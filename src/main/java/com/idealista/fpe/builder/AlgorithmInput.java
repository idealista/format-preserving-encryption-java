package com.idealista.fpe.builder;

import com.idealista.fpe.algorithm.Cipher;
import com.idealista.fpe.component.functions.prf.PseudoRandomFunction;
import com.idealista.fpe.config.Domain;
import com.idealista.fpe.config.LengthRange;

public class AlgorithmInput {
    private final Cipher cipher;
    private final Domain selectedDomain;
    private final PseudoRandomFunction selectedPRF;
    private final LengthRange lengthRange;

    AlgorithmInput(Cipher cipher, Domain selectedDomain, PseudoRandomFunction selectedPRF, LengthRange lengthRange) {
        this.cipher = cipher;
        this.selectedDomain = selectedDomain;
        this.selectedPRF = selectedPRF;
        this.lengthRange = lengthRange;
    }

    public Cipher getCipher() {
        return cipher;
    }

    public Domain getSelectedDomain() {
        return selectedDomain;
    }

    public PseudoRandomFunction getSelectedPRF() {
        return selectedPRF;
    }

    public LengthRange getLengthRange() {
        return lengthRange;
    }
}
