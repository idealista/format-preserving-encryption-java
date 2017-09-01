package com.idealista.fpe.component.functions;

import java.math.BigInteger;

public class ComponentFunctions {

    public static BigInteger num(int[] plainData, Integer radix) {
        BigInteger result = BigInteger.ZERO;
        BigInteger bradix = BigInteger.valueOf(radix);
        for (int number : plainData) {
            result = result.multiply(bradix).add(BigInteger.valueOf(number));
        }
        return result;
    }
}
