package com.idealista.fpe.component.functions;

import java.math.BigInteger;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class ComponentFunctions {

    public static BigInteger num(int[] plainData, Integer radix) {
        BigInteger result = BigInteger.ZERO;
        BigInteger base = BigInteger.valueOf(radix);
        for (int number : plainData) {
            result = result.multiply(base).add(BigInteger.valueOf(number));
        }
        return result;
    }

    public static BigInteger num(byte[] plainData) {
        return new BigInteger(1, plainData);
    }

    public static int[] stringOf(Integer length, Integer radix, BigInteger number) {
        BigInteger base = BigInteger.valueOf(radix);
        UtilFunctions.checkRangeOf(number, base.pow(length));
        int[] result = new int[length];
        BigInteger workingNumber = number;
        for (int i = 1; i <= length; i++) {
            result[length - i] = workingNumber.mod(base).intValue();
            workingNumber = workingNumber.divide(base);
        }
        return result;
    }

    public static byte[] PRF(byte[] plain, byte[] key) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        byte[] initializationVector = {(byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
                (byte) 0x00, (byte) 0x00};
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(initializationVector));
        byte[] result = cipher.doFinal(plain);
        return Arrays.copyOfRange(result, result.length - 16, result.length);
    }

    public static byte[] ciph(byte[] key, byte[] plain) throws InvalidKeyException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, NoSuchAlgorithmException {
        byte[] cipherText;
        Cipher cipher = Cipher.getInstance("AES/CBC/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(key, "AES"));
        cipherText = cipher.doFinal(plain);
        return cipherText;
    }

}
