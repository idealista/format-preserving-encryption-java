package com.idealista.fpe.builder.steps;

import com.idealista.fpe.config.Domain;

public interface WithDomain {
    WithPseudoRandomFunction withDomain(Domain basicAlphabetDomain);

    WithPseudoRandomFunction withDefaultDomain();
}
