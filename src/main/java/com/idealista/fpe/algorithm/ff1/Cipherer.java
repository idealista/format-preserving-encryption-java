package com.idealista.fpe.algorithm.ff1;

import com.idealista.fpe.component.functions.prf.PseudoRandomFunction;

public class Cipherer implements com.idealista.fpe.algorithm.Cipherer{

    @Override
    public int[] encrypt(int[] plainText, Integer radix, byte[] tweak, PseudoRandomFunction pseudoRandomFunction) {
        return FF1Algorithm.encrypt(plainText, radix, tweak, pseudoRandomFunction);
    }

    @Override
    public int[] dencrypt(int[] cipherText, Integer radix, byte[] tweak, PseudoRandomFunction pseudoRandomFunction) {
        return FF1Algorithm.decrypt(cipherText, radix, tweak, pseudoRandomFunction);
    }
}
