package com.idealista.fpe.component.functions.prf;

public interface PseudorandomFunction {
    public byte[] apply(byte[] text, byte[] key);
}
