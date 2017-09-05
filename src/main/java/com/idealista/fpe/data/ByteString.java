package com.idealista.fpe.data;

public class ByteString {

    private final byte[] data;

    public ByteString(byte[] data) {
        this.data = data;
    }

    public ByteString concatenate(ByteString other) {
        byte[] result = new byte[data.length + other.length()];
        System.arraycopy(data, 0, result, 0, data.length);
        System.arraycopy(other.raw(), 0, result, data.length, other.length());
        return new ByteString(result);
    }

    public int length() {
        return data.length;
    }

    public byte[] raw() {
        return data;
    }

    public byte get(int position) {
        return data[position];
    }

    public byte[] getData() {
        return data;
    }
}
