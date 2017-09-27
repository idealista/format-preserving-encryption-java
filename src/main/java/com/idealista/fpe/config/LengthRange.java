package com.idealista.fpe.config;

public class LengthRange {
    private static final String RANGE_FORMAT = "[%d, %d]";
    private final Integer min;
    private final Integer max;

    public LengthRange(Integer min, Integer max) {
        this.min = min;
        this.max = max;
    }

    public Integer min() {
        return min;
    }

    public Integer max() {
        return max;
    }

    @Override
    public String toString() {
        return String.format(RANGE_FORMAT, min, max);
    }
}
