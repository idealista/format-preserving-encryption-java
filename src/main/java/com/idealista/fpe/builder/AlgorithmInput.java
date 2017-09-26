package com.idealista.fpe.builder;

import com.idealista.fpe.algorithm.Cipherer;
import com.idealista.fpe.component.functions.prf.PseudoRandomFunction;
import com.idealista.fpe.config.Domain;
import com.idealista.fpe.config.LengthRange;

public class AlgorithmInput {
    private final Cipherer cipherer;
    private final Domain selectedDomain;
    private final PseudoRandomFunction selectedPRF;
    private final LengthRange lengthRange;

    AlgorithmInput(Cipherer cipherer, Domain selectedDomain, PseudoRandomFunction selectedPRF, LengthRange lengthRange) {
        this.cipherer = cipherer;
        this.selectedDomain = selectedDomain;
        this.selectedPRF = selectedPRF;
        this.lengthRange = lengthRange;
    }

    public Cipherer getCipherer() {
        return cipherer;
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
