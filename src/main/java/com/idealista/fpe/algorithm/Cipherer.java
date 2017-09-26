package com.idealista.fpe.algorithm;

import com.idealista.fpe.component.functions.prf.PseudoRandomFunction;

public interface Cipherer {
    int[] encrypt(int[] plainText, Integer radix, byte[] tweak, PseudoRandomFunction pseudoRandomFunction);
    int[] dencrypt(int[] cipherText, Integer radix, byte[] tweak, PseudoRandomFunction pseudoRandomFunction);
}
