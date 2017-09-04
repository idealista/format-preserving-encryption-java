package com.idealista.fpe.data;

public class IntString {

    private final int[] data;

    public IntString(int[] data) {
        this.data = data;
    }

    public int leftSideLength() {
        return (int) Math.floor(this.length() / 2.0);
    }

    public int rightSideLength() {
        return this.length() - this.leftSideLength();
    }

    public int length() {
        return data.length;
    }
}
