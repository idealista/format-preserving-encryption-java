package com.idealista.fpe.data;

import java.util.Arrays;

public class IntString {

    private final int[] data;

    public IntString(int[] data) {
        this.data = data;
    }

    public int leftSideLength() {
        return (int) Math.floor(length() / 2.0);
    }

    public int rightSideLength() {
        return length() - leftSideLength();
    }

    public int[] left() {
        return Arrays.copyOfRange(data, 0, leftSideLength());
    }

    public int[] right() {
        return Arrays.copyOfRange(data, leftSideLength(), length());
    }

    public int length() {
        return data.length;
    }
}
