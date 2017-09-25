package com.idealista.fpe;

import com.idealista.fpe.algorithm.Cipherer;
import com.idealista.fpe.component.functions.prf.PseudorandomFunction;
import com.idealista.fpe.config.Domain;
import com.idealista.fpe.config.LengthRange;

public class FormatPreservingEncryption {
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
        int[] numeralPlainText = selectedDomain.textToIntTransformer().transform(plainText);
        int[] numeralCipher = cipherer.encrypt(numeralPlainText, selectedDomain.alphabet().radix(), tweak, selectedPRF);
        return selectedDomain.intToTextTransformer().transform(numeralCipher);
    }

    public String decrypt(String cipherText, byte[] tweak) {
        int[] numeralCipherText = selectedDomain.textToIntTransformer().transform(cipherText);
        int[] numeralPlainText = cipherer.dencrypt(numeralCipherText, selectedDomain.alphabet().radix(), tweak, selectedPRF);
        return selectedDomain.intToTextTransformer().transform(numeralPlainText);
    }
}
