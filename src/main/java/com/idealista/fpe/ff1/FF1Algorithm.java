package com.idealista.fpe.ff1;

import static com.idealista.fpe.component.functions.ComponentFunctions.num;
import static com.idealista.fpe.component.functions.ComponentFunctions.stringOf;
import static com.idealista.fpe.component.functions.UtilFunctions.*;
import static java.lang.Math.ceil;

import java.math.BigInteger;
import java.util.Arrays;

import com.idealista.fpe.component.functions.prf.PseudorandomFunction;
import com.idealista.fpe.data.ByteString;
import com.idealista.fpe.data.IntString;

public class FF1Algorithm {

    public static int[] encrypt(int[] plainText, Integer radix, byte[] tweak, PseudorandomFunction pseudorandomFunction) {
        IntString target = new IntString(plainText);
        int tweakLength = tweak.length;
        int leftSideLength = target.leftSideLength();
        int rightSideLength = target.rightSideLength();
        int[] left = target.left();
        int[] right = target.right();
        int lengthOfLeftAfterEncoded = lengthOfLeftAfterEncoded(radix, rightSideLength);
        int paddingToEnsureFeistelOutputIsBigger = (int) (4 * ceil(lengthOfLeftAfterEncoded / 4.0) + 4);
        ByteString padding = generateInitialPadding(radix, target.length(), tweakLength, leftSideLength);
        for (int i = 0; i < 10; i++) {
            ByteString q = new ByteString(tweak)
                    .concatenate(numberAsArrayOfBytes(0, mod(-tweakLength - lengthOfLeftAfterEncoded - 1, 16)))
                    .concatenate(numberAsArrayOfBytes(i, 1))
                    .concatenate(numberAsArrayOfBytes(num(right, radix), lengthOfLeftAfterEncoded));
            byte[] R = pseudorandomFunction.apply(padding.concatenate(q).raw());
            ByteString S = new ByteString(R);
            for (int j = 1; j <= ceil(paddingToEnsureFeistelOutputIsBigger / 16.0) - 1; j++) {
                S = S.concatenate(new ByteString(pseudorandomFunction.apply(xor(R, numberAsArrayOfBytes(j, 16).getData()))));
            }
            BigInteger y = num(Arrays.copyOf(S.raw(), paddingToEnsureFeistelOutputIsBigger));
            int m = i % 2 == 0 ? leftSideLength : rightSideLength;
            BigInteger c = num(left, radix).add(y).mod(BigInteger.valueOf(radix).pow(m));
            int[] partialSide = stringOf(m, radix, c);
            left = right;
            right = partialSide;
        }
        return concatenate(left, right);
    }

    private static int lengthOfLeftAfterEncoded(Integer radix, int rightSideLength) {
        return (int) ceil(ceil(rightSideLength * log(radix)) / 8.0);
    }


    public static int[] decrypt(int[] cipherText, Integer radix, byte[] tweak, PseudorandomFunction pseudorandomFunction) {
        int textLength = cipherText.length;
        int tweakLength = tweak.length;
        int leftSideLength = (int) Math.floor(textLength / 2.0);
        int rightSideLength = textLength - leftSideLength;
        int[] left = Arrays.copyOfRange(cipherText, 0, leftSideLength);
        int[] right = Arrays.copyOfRange(cipherText, leftSideLength, textLength);
        int b = lengthOfLeftAfterEncoded(radix, rightSideLength);
        int d = (int) (4 * ceil(b / 4.0) + 4);
        ByteString padding = generateInitialPadding(radix, textLength, tweakLength, leftSideLength);
        for (int i = 9; i >= 0; i--) {
            byte[] Q = concatenate(tweak, numberAsArrayOfBytes(0, mod(-tweakLength - b - 1, 16)).getData());
            Q = concatenate(Q, numberAsArrayOfBytes(i, 1).getData());
            Q = concatenate(Q, numberAsArrayOfBytes(num(left, radix), b).getData());
            byte[] R = pseudorandomFunction.apply(padding.concatenate(new ByteString(Q)).raw());
            byte[] S = R;
            for (int j = 1; j <= ceil(d / 16.0) - 1; j++) {
                S = concatenate(S, pseudorandomFunction.apply(xor(R, numberAsArrayOfBytes(j, 16).getData())));
            }
            S = Arrays.copyOf(S, d);
            BigInteger y = num(S);
            int m = i % 2 == 0 ? leftSideLength : rightSideLength;
            BigInteger c = num(right, radix).subtract(y).mod(BigInteger.valueOf(radix).pow(m));

            int[] partialSide = stringOf(m, radix, c);
            right = left;
            left = partialSide;
        }
        return concatenate(left, right);
    }

    private static ByteString generateInitialPadding(Integer radix, int textLength, int tweakLength, int leftSideLength) {
        return new ByteString(new byte[]{ (byte) 0x01, (byte) 0x02, (byte) 0x01 } )
                .concatenate(numberAsArrayOfBytes(radix, 3))
                .concatenate(new ByteString( new byte[] { (byte) 0x0A } ))
                .concatenate(new ByteString( new byte[] { (byte) (mod(leftSideLength, 256) & 0xFF) }))
                .concatenate(numberAsArrayOfBytes(textLength, 4))
                .concatenate(numberAsArrayOfBytes(tweakLength, 4));
    }
}
