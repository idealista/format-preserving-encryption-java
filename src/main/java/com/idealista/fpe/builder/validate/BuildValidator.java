package com.idealista.fpe.builder.validate;

public class BuildValidator {

    private static final String OUT_OF_RANGE_RADIX = "radix not in: [ 2 .. 2 ^ 16 ]";
    private static final String OUT_OF_RANGE_MIN_LENGTH = "minLength not in: [ 2 .. %d )";
    private static final String RADIX_TO_MIN_LENGTH_LOWER_THAN_LIMIT = "radix ^ minLength is lower than 100";
    private static final int TEXT_VALID_MIN_LENGTH = 2;

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
        if (radix < TEXT_VALID_MIN_LENGTH || radix > Math.pow(2d, 16d))
            throw new IllegalArgumentException(OUT_OF_RANGE_RADIX);
    }

    void textSizeLimits() {
        if (minLength < TEXT_VALID_MIN_LENGTH || minLength >= maxLength)
            throw new IllegalArgumentException(String.format(OUT_OF_RANGE_MIN_LENGTH, maxLength));
    }

    void radixToMinLength() {
        if (Math.pow(radix, minLength) <= 100)
            throw new IllegalArgumentException(RADIX_TO_MIN_LENGTH_LOWER_THAN_LIMIT);
    }
}
