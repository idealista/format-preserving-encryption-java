package com.idealista.fpe;

import com.idealista.fpe.algorithm.Cipherer;
import com.idealista.fpe.component.functions.prf.PseudorandomFunction;
import com.idealista.fpe.config.Domain;
import com.idealista.fpe.config.LengthRange;

public class FormatPreservingEncryption {
    public static final String INVALID_SIZE = "invalid size, out of range: ";
    private final Cipherer cipherer;
    private final Domain selectedDomain;
    private final PseudorandomFunction selectedPRF;
    private final LengthRange lengthRange;

    public FormatPreservingEncryption(Cipherer cipherer, Domain selectedDomain, PseudorandomFunction selectedPRF, LengthRange lengthRange) {
        this.cipherer = cipherer;
        this.selectedDomain = selectedDomain;
        this.selectedPRF = selectedPRF;
        this.lengthRange = lengthRange;
    }

    public String encrypt(String plainText, byte[] tweak) {
        lengthRange.validate(plainText);
        int[] numeralPlainText = selectedDomain.transform(plainText);
        int[] numeralCipher = cipherer.encrypt(numeralPlainText, selectedDomain.alphabet().radix(), tweak, selectedPRF);
        return selectedDomain.transform(numeralCipher);
    }

    public String decrypt(String cipherText, byte[] tweak) {
        lengthRange.validate(cipherText);
        int[] numeralCipherText = selectedDomain.transform(cipherText);
        int[] numeralPlainText = cipherer.dencrypt(numeralCipherText, selectedDomain.alphabet().radix(), tweak, selectedPRF);
        return selectedDomain.transform(numeralPlainText);
    }
}
