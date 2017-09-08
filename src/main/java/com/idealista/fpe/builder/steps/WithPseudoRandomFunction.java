package com.idealista.fpe.builder.steps;

import com.idealista.fpe.component.functions.prf.PseudorandomFunction;

public interface WithPseudoRandomFunction {
    WithLengthRange withPseudoRandomFunction(PseudorandomFunction pseudoRandomFunction);

    WithLengthRange withDefaultPseudoRandomFunction(byte[] anyKey);
}
