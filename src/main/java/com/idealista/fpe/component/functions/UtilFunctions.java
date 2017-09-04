package com.idealista.fpe.component.functions;

import java.math.BigInteger;

class UtilFunctions {

    private static final String NUMBER_IS_OUT_OF_RANGE = "number is out of range: [0, + %s )";

    private UtilFunctions(){}

    static void checkRangeOf(BigInteger number, BigInteger upperLimit) {
        if (number.compareTo(BigInteger.ZERO) < 0 || number.compareTo(upperLimit) >= 0)
            throw new IllegalArgumentException(String.format(NUMBER_IS_OUT_OF_RANGE, upperLimit));
    }

    static int log(int number) {
        checkRangeOf(BigInteger.valueOf(number), BigInteger.valueOf(Integer.MAX_VALUE));
        return (int) (Math.log(number) / Math.log(2));
    }
}
