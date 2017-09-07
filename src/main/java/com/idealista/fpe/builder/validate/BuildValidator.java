package com.idealista.fpe.builder.validate;

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

    public void validate () {
        radix();
        radixToMinLength();
        textSizeLimits();
        tweakSize();

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
        if (Math.pow(radix, minLength) > 100)
            throw new IllegalArgumentException("radix ^ minLength is greater than 100");
    }

    void tweakSize() {
        if (tweak.length > maxLength)
            throw new IllegalArgumentException("tweak length should be less than maxLength");
    }
}
