package com.idealista.fpe.algorithm;

import com.idealista.fpe.component.functions.prf.PseudorandomFunction;

public interface Cipherer {
    int[] encrypt(int[] plainText, Integer radix, byte[] tweak, PseudorandomFunction pseudorandomFunction);
    int[] dencrypt(int[] cipherText, Integer radix, byte[] tweak, PseudorandomFunction pseudorandomFunction);
}
