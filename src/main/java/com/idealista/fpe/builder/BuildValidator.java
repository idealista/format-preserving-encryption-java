package com.idealista.fpe.builder;

import java.util.Map;

public class BuildValidator {

    private final int radix;
    private final int minLength;
    private final int maxLength;
    private final byte[] tweak;

    public BuildValidator(int radix, int minLength, int maxLength, byte[] tweak) {
        this.radix = radix;
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.tweak = tweak;
    }

    public void radix() {
        if (radix < 2 || radix > Math.pow(2d, 16d))
            throw new IllegalArgumentException("radix not in: [ 2 .. 2 ^ 16 ]");
    }

    public void textSize() {
        if (minLength < 2  || minLength >= maxLength)
            throw new IllegalArgumentException("minLength not in: [ 2 .." + maxLength + " )");
    }

    public void radixToMinLength() {
        if (Math.pow(radix, minLength) > 100)
            throw new IllegalArgumentException("radix ^ minLength is greater than 100");
    }

    public void tweakSize() {
        if (tweak.length > maxLength)
            throw new IllegalArgumentException("tweak length should be less than maxLength");
    }
}
