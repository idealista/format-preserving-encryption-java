package com.idealista.fpe.algorithm.ff1;

import com.idealista.fpe.component.functions.prf.PseudoRandomFunction;

public class Cipher implements com.idealista.fpe.algorithm.Cipher {

    @Override
    public int[] encrypt(int[] plainText, Integer radix, byte[] tweak, PseudoRandomFunction pseudoRandomFunction) {
        return FF1Algorithm.encrypt(plainText, radix, tweak, pseudoRandomFunction);
    }

    @Override
    public int[] decrypt(int[] cipherText, Integer radix, byte[] tweak, PseudoRandomFunction pseudoRandomFunction) {
        return FF1Algorithm.decrypt(cipherText, radix, tweak, pseudoRandomFunction);
    }
}
