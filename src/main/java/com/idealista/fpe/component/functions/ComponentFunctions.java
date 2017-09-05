package com.idealista.fpe.component.functions;

import java.math.BigInteger;

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
            result[length - i] = workingNumber.mod(base).intValue(); // int value conversion is save since base is an integer
            workingNumber = workingNumber.divide(base);
        }
        return result;
    }

}
