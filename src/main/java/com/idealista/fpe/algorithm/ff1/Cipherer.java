package com.idealista.fpe.algorithm.ff1;

import com.idealista.fpe.component.functions.prf.PseudorandomFunction;

public class Cipherer implements com.idealista.fpe.algorithm.Cipherer{

    @Override
    public int[] encrypt(int[] plainText, Integer radix, byte[] tweak, PseudorandomFunction pseudorandomFunction) {
        return FF1Algorithm.encrypt(plainText, radix, tweak, pseudorandomFunction);
    }

    @Override
    public int[] dencrypt(int[] cipherText, Integer radix, byte[] tweak, PseudorandomFunction pseudorandomFunction) {
        return FF1Algorithm.decrypt(cipherText, radix, tweak, pseudorandomFunction);
    }
}
