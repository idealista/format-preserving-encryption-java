package com.idealista.fpe.component.functions;

import java.math.BigInteger;
import java.net.BindException;

public class ComponentFunctions {

    public static BigInteger num(int[] plainData, Integer radix) {
        BigInteger result = BigInteger.ZERO;
        BigInteger bradix = BigInteger.valueOf(radix);
        for (int number : plainData) {
            result = result.multiply(bradix).add(BigInteger.valueOf(number));
        }
        return result;
    }

    public static BigInteger num(byte[] plainData) {
        return new BigInteger(1, plainData);
    }

    public static int[] stringOf(Integer length, Integer radix, BigInteger number) {
        int[] result = new int[length];
        BigInteger base = BigInteger.valueOf(radix);
        if (number.compareTo(BigInteger.ZERO) < 0 || number.compareTo(base.pow(length)) >= 0)
            throw new IllegalArgumentException("number is out of range: [0, + "+ base.pow(length)  +" )");
        BigInteger workingNumber = number;
        for (int i=1; i <= length; i++){
            result[length - i] = workingNumber.mod(base).intValue();
            workingNumber = workingNumber.divide(base);
        }
        return result;
    }
}
