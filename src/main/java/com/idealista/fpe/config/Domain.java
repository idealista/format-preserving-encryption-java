package com.idealista.fpe.config;

public interface Domain {
    Alphabet alphabet();
    int[] transform(String data);
    String transform(int[] data);
}
