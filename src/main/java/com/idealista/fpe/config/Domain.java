package com.idealista.fpe.config;

public interface Domain {
    public Alphabet alphabet();
    public int[] transform(String data);
    public String transform(int[] data);
}
