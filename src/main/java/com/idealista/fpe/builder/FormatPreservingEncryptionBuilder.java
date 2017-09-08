package com.idealista.fpe.builder;

import com.idealista.fpe.algorithm.ff1.Cipherer;
import com.idealista.fpe.builder.steps.WithDomain;
import com.idealista.fpe.builder.steps.WithPseudoRandomFunction;
import com.idealista.fpe.config.basic.BasicAlphabetDomain;

public class FormatPreservingEncryptionBuilder {

    private static final com.idealista.fpe.algorithm.Cipherer ff1 = new Cipherer();


    public static WithDomain ff1Implementation() {
        return new WithDomain() {
            @Override
            public WithPseudoRandomFunction withDomain(BasicAlphabetDomain basicAlphabetDomain) {
                return null;
            }

            @Override
            public WithPseudoRandomFunction withDefaultDomain() {
                return null;
            }
        };
    }
}
