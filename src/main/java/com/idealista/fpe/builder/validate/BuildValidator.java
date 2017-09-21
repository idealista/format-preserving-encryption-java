package com.idealista.fpe.builder.validate;

public class BuildValidator {

    private final int radix;
    private final int minLength;
    private final int maxLength;

    public BuildValidator(int radix, int minLength, int maxLength) {
        this.radix = radix;
        this.minLength = minLength;
        this.maxLength = maxLength;
    }

    public void validate () {
        radix();
        radixToMinLength();
        textSizeLimits();
    }

    void radix() {
        if (radix < 2 || radix > Math.pow(2d, 16d))
            throw new IllegalArgumentException("radix not in: [ 2 .. 2 ^ 16 ]");
    }

    void textSizeLimits() {
        if (minLength < 2  || minLength >= maxLength)
            throw new IllegalArgumentException("minLength not in: [ 2 .." + maxLength + " )");
    }

    void radixToMinLength() {
        if (Math.pow(radix, minLength) <= 100)
            throw new IllegalArgumentException("radix ^ minLength is lower than 100");
    }
}
