package com.idealista.fpe.component.functions;

import java.math.BigInteger;
import java.util.Arrays;

public class UtilFunctions {

    private static final String NUMBER_IS_OUT_OF_RANGE = "number is out of range: [0, + %s )";

    private UtilFunctions(){}

    static void checkRangeOf(BigInteger number, BigInteger upperLimit) {
        if (number.compareTo(BigInteger.ZERO) < 0 || number.compareTo(upperLimit) >= 0)
            throw new IllegalArgumentException(String.format(NUMBER_IS_OUT_OF_RANGE, upperLimit));
    }

    public static int log(int number) {
        checkRangeOf(BigInteger.valueOf(number), BigInteger.valueOf(Integer.MAX_VALUE));
        return (int) (Math.log(number) / Math.log(2));
    }

    public static byte[] numberAsArrayOfBytes(int number, int length) {
        checkRangeOf(BigInteger.valueOf(number), BigInteger.valueOf(Integer.MAX_VALUE));
        byte[] bytes = new byte[length];
        int transformableNumber = number;
        for (int i = length - 1; i >= 0 ; i--) {
            if(transformableNumber < 0) break;
            bytes[i] = (byte) (transformableNumber & 0xFF);
            transformableNumber >>>= 8;
        }
        return bytes;
    }
    public static byte[] bitstring(boolean bit, int s) {
        // validate s
        if (s < 1)
            throw new IllegalArgumentException("s must be a positive integer");
        if (s % 8 != 0)
            throw new IllegalArgumentException("s must be a multiple of 8: " + s);

        byte[] string = new byte[s / 8];

        Arrays.fill(string, bit ? (byte) 0xFF : (byte) 0x00);

        return string;
    }


    public static byte[] concatenate(byte[] left, byte[] right) {
        byte[] result = new byte[left.length + right.length];
        System.arraycopy(left, 0, result, 0, left.length);
        System.arraycopy(right, 0, result, left.length, right.length);
        return result;
    }

    public static int[] concatenate(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];
        System.arraycopy(left, 0, result, 0, left.length);
        System.arraycopy(right, 0, result, left.length, right.length);
        return result;
    }

    public static byte[] xor(byte[] left, byte[] right) {
        byte[] result = new byte[left.length];
        for (int i = 0; i < right.length; i++) {
            result[i] = (byte) (left[i] ^ right[i]);
        }

        return result;
    }

    public static int mod(int number, int module) {
        if (module < 1)
            throw new ArithmeticException("module must be a positive integer");
        return (int) (number - module * Math.floor(number / (double) module));
    }
}
