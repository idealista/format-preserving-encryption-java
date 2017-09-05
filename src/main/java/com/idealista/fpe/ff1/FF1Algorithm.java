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

class FF1Algorithm {

    static int[] encrypt(int[] plainText, Integer radix, byte[] tweak, PseudorandomFunction pseudorandomFunction) {
        IntString target = new IntString(plainText);
        ByteString tweakString = new ByteString(tweak);
        int leftSideLength = target.leftSideLength();
        int rightSideLength = target.rightSideLength();
        int[] left = target.left();
        int[] right = target.right();
        int lengthOfLeftAfterEncoded = (int) ceil(ceil(rightSideLength * log(radix)) / 8.0);
        int paddingToEnsureFeistelOutputIsBigger = (int) (4 * ceil(lengthOfLeftAfterEncoded / 4.0) + 4);
        ByteString padding = generateInitialPadding(radix, target.length(), tweakString.length(), leftSideLength);

        for (int i = 0; i < 10; i++) {
            BigInteger targetSideNumeral = num(right, radix);
            ByteString q = generateQ(tweakString, targetSideNumeral, lengthOfLeftAfterEncoded, i);
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


    static int[] decrypt(int[] cipherText, Integer radix, byte[] tweak, PseudorandomFunction pseudorandomFunction) {
        IntString target = new IntString(cipherText);
        ByteString tweakString = new ByteString(tweak);
        int leftSideLength = target.leftSideLength();
        int rightSideLength = target.rightSideLength();
        int[] left = target.left();
        int[] right = target.right();
        int lengthOfLeftAfterEncoded = (int) ceil(ceil(rightSideLength * log(radix)) / 8.0);
        int paddingToEnsureFeistelOutputIsBigger = (int) (4 * ceil(lengthOfLeftAfterEncoded / 4.0) + 4);
        ByteString padding = generateInitialPadding(radix, target.length(), tweakString.length(), leftSideLength);

        for (int i = 9; i >= 0; i--) {
            BigInteger targetSideNumeral = num(left, radix);
            ByteString q = generateQ(tweakString, targetSideNumeral, lengthOfLeftAfterEncoded, i);
            byte[] R = pseudorandomFunction.apply(padding.concatenate(q).raw());
            byte[] S = R;
            for (int j = 1; j <= ceil(paddingToEnsureFeistelOutputIsBigger / 16.0) - 1; j++) {
                S = concatenate(S, pseudorandomFunction.apply(xor(R, numberAsArrayOfBytes(j, 16).getData())));
            }
            S = Arrays.copyOf(S, paddingToEnsureFeistelOutputIsBigger);
            BigInteger y = num(S);
            int m = i % 2 == 0 ? leftSideLength : rightSideLength;
            BigInteger c = num(right, radix).subtract(y).mod(BigInteger.valueOf(radix).pow(m));

            int[] partialSide = stringOf(m, radix, c);
            right = left;
            left = partialSide;
        }
        return concatenate(left, right);
    }

    private static ByteString generateQ(ByteString tweak, BigInteger targetSideNumeral, int lengthOfLeftAfterEncoded, int round) {
        return tweak
                .concatenate(numberAsArrayOfBytes(0, mod(- tweak.length() - lengthOfLeftAfterEncoded - 1, 16)))
                .concatenate(numberAsArrayOfBytes(round, 1))
                .concatenate(numberAsArrayOfBytes(targetSideNumeral, lengthOfLeftAfterEncoded));
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
