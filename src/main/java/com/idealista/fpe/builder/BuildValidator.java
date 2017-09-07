package com.idealista.fpe.builder;

import java.util.Map;

public class BuildValidator {

    public static void radix(int radix) {
        if (radix < 2 || radix > Math.pow(2d, 16d))
            throw new IllegalArgumentException("radix not in: [ 2 .. 2 ^ 16 ]");
    }
}
