package com.idealista.fpe.ff1;

import static com.idealista.fpe.component.functions.ComponentFunctions.num;
import static com.idealista.fpe.component.functions.ComponentFunctions.stringOf;
import static com.idealista.fpe.component.functions.UtilFunctions.*;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import com.idealista.fpe.component.functions.ComponentFunctions;

public class FF1Algorithm {

    public FF1Algorithm(Integer radix, Integer maxTweakLength) {

    }

    public static int[] encrypt(int[] plainText, Integer radix, byte[] key, byte[] tweak, Cipher cipher) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, InvalidAlgorithmParameterException, NoSuchAlgorithmException, NoSuchPaddingException {
        int textLength = plainText.length;
        int tweakLength = tweak.length;
        int leftSideLength = (int) Math.floor(textLength / 2.0);
        int rightSideLength = textLength - leftSideLength;
        int[] left = Arrays.copyOfRange(plainText, 0, leftSideLength);
        int[] right = Arrays.copyOfRange(plainText, leftSideLength, textLength);
        int b = (int) Math.ceil(Math.ceil(rightSideLength * log(radix)) / 8.0);
        int d = (int) (4 * Math.ceil(b / 4.0) + 4);
        byte[] radixAsByteArray = numberAsArrayOfBytes(radix, 3);
        byte[] lengthAsByteArray = numberAsArrayOfBytes(textLength, 4);
        byte[] tLengthAsByteArray = numberAsArrayOfBytes(tweakLength, 4);
        byte[] padding = { (byte) 0x01, (byte) 0x02, (byte) 0x01, radixAsByteArray[0], radixAsByteArray[1], radixAsByteArray[2], (byte) 0x0A,
                (byte) (mod(leftSideLength, 256) & 0xFF), lengthAsByteArray[0], lengthAsByteArray[1], lengthAsByteArray[2], lengthAsByteArray[3], tLengthAsByteArray[0], tLengthAsByteArray[1], tLengthAsByteArray[2], tLengthAsByteArray[3] };
        for (int i = 0; i < 10; i++) {
            byte[] q = concatenate(tweak, numberAsArrayOfBytes(0, mod(-tweakLength - b - 1, 16)));
            q = concatenate(q, numberAsArrayOfBytes(i, 1));
            q = concatenate(q, numberAsArrayOfBytes(num(right, radix).intValue(), b));
            byte[] R = ComponentFunctions.prf(concatenate(padding, q), key);
            byte[] S = R;
            for (int j = 1; j <= Math.ceil(d / 16.0) - 1; j++) {
                S = concatenate(S, ComponentFunctions.ciph(key, xor(R, numberAsArrayOfBytes(j, 16)), cipher));
            }
            S = Arrays.copyOf(S, d);
            BigInteger y = num(S);
            int m = i % 2 == 0 ? leftSideLength : rightSideLength;
            BigInteger c = num(left, radix).add(y).mod(BigInteger.valueOf(radix).pow(m));
            int[] partialSide = stringOf(m, radix, c);
            left = right;
            right = partialSide;
        }
        return concatenate(left, right);
    }

    public static int[] decrypt(int[] cipherText, Integer radix, byte[] key, byte[] tweak, Cipher cipher) throws IllegalBlockSizeException, BadPaddingException, InvalidAlgorithmParameterException, InvalidKeyException {
        int textLength = cipherText.length;
        int tweakLength = tweak.length;
        int leftSideLength = (int) Math.floor(textLength / 2.0);
        int rightSideLength = textLength - leftSideLength;
        int[] left = Arrays.copyOfRange(cipherText, 0, leftSideLength);
        int[] right = Arrays.copyOfRange(cipherText, leftSideLength, textLength);
        int b = (int) Math.ceil(Math.ceil(rightSideLength * log(radix)) / 8.0);
        int d = (int) (4 * Math.ceil(b / 4.0) + 4);
        byte[] tbr = numberAsArrayOfBytes(radix, 3);
        byte[] fbn = numberAsArrayOfBytes(textLength, 4);
        byte[] fbt = numberAsArrayOfBytes(tweakLength, 4);
        byte[] P = { (byte) 0x01, (byte) 0x02, (byte) 0x01, tbr[0], tbr[1], tbr[2], (byte) 0x0A,
                (byte) (mod(leftSideLength, 256) & 0xFF), fbn[0], fbn[1], fbn[2], fbn[3], fbt[0], fbt[1], fbt[2], fbt[3] };
        for (int i = 9; i >= 0; i--) {
            byte[] Q = concatenate(tweak, numberAsArrayOfBytes(0, mod(-tweakLength - b - 1, 16)));
            Q = concatenate(Q, numberAsArrayOfBytes(i, 1));
            Q = concatenate(Q, numberAsArrayOfBytes(num(left, radix).intValue(), b));
            byte[] R = ComponentFunctions.prf2(concatenate(P, Q), key, cipher);
            byte[] S = R;
            for (int j = 1; j <= Math.ceil(d / 16.0) - 1; j++) {
                S = concatenate(S, ComponentFunctions.ciph(key, xor(R, numberAsArrayOfBytes(j, 16)), cipher));
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
}
