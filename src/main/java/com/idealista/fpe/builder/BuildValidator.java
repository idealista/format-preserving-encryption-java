package com.idealista.fpe.builder;

import java.util.Map;

public class BuildValidator {

    public static void radix(int radix) {
        if (radix < 2 || radix > Math.pow(2d, 16d))
            throw new IllegalArgumentException("radix not in: [ 2 .. 2 ^ 16 ]");
    }

    public static void textSize(int minLength, int maxLength) {
        if (minLength < 2  || minLength >= maxLength)
            throw new IllegalArgumentException("minLength not in: [ 2 .." + maxLength + " )");
    }

    public static void radixToMinLength(int radix, int minLength) {
        if (Math.pow(radix, minLength) > 100)
            throw new IllegalArgumentException("radix ^ minLength is greater than 100");
    }

    public static void tweakSize(byte[] tweak, int maxLength) {
        if (tweak.length > maxLength)
            throw new IllegalArgumentException("tweak length should be less than maxLength");
    }
}
