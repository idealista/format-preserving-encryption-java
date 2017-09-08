package com.idealista.fpe.builder.steps;

import com.idealista.fpe.config.basic.BasicAlphabetDomain;

public interface WithDomain {
    WithPseudoRandomFunction withDomain(BasicAlphabetDomain basicAlphabetDomain);

    WithPseudoRandomFunction withDefaultDomain();
}
