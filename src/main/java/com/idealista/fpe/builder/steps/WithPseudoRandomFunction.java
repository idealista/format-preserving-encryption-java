package com.idealista.fpe.builder.steps;

import com.idealista.fpe.component.functions.prf.PseudoRandomFunction;

public interface WithPseudoRandomFunction {
    WithLengthRange withPseudoRandomFunction(PseudoRandomFunction pseudoRandomFunction);

    WithLengthRange withDefaultPseudoRandomFunction(byte[] anyKey);
}
