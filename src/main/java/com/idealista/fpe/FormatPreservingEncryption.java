package com.idealista.fpe;

import static com.idealista.fpe.FormatPreservingEncryptionErrorMessage.*;

import com.idealista.fpe.algorithm.Cipher;
import com.idealista.fpe.builder.AlgorithmInput;
import com.idealista.fpe.component.functions.prf.PseudoRandomFunction;
import com.idealista.fpe.config.Domain;
import com.idealista.fpe.config.LengthRange;

public class FormatPreservingEncryption {

    private final Cipher cipher;
    private final Domain selectedDomain;
    private final PseudoRandomFunction selectedPRF;
    private final LengthRange lengthRange;

    public FormatPreservingEncryption(AlgorithmInput algorithmInput) {
        this.cipher = algorithmInput.getCipher();
        this.selectedDomain = algorithmInput.getSelectedDomain();
        this.selectedPRF = algorithmInput.getSelectedPRF();
        this.lengthRange = algorithmInput.getLengthRange();
    }

    public String encrypt(String plainText, byte[] tweak) {
        check(plainText, tweak);
        int[] numeralPlainText = selectedDomain.transform(plainText);
        int[] numeralCipher = cipher.encrypt(numeralPlainText, selectedDomain.alphabet().radix(), tweak, selectedPRF);
        return selectedDomain.transform(numeralCipher);
    }

    public String decrypt(String cipherText, byte[] tweak) {
        check(cipherText, tweak);
        int[] numeralCipherText = selectedDomain.transform(cipherText);
        int[] numeralPlainText = cipher.decrypt(numeralCipherText, selectedDomain.alphabet().radix(), tweak, selectedPRF);
        return selectedDomain.transform(numeralPlainText);
    }

    private void check(String text, byte[] tweak) {
        if (text == null || tweak == null)
            throw  new IllegalArgumentException(NULL_INPUT.toString());

        if (text.length() < lengthRange.min() || text.length() > lengthRange.max())
            throw  new IllegalArgumentException(INVALID_SIZE + lengthRange.toString());

        if (tweak.length > lengthRange.max())
            throw  new IllegalArgumentException(TWEAK_INVALID_SIZE.toString() + lengthRange.max());

    }
}
