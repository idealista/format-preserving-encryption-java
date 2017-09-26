package com.idealista.fpe.builder;

import com.idealista.fpe.FormatPreservingEncryption;
import com.idealista.fpe.algorithm.ff1.Cipherer;
import com.idealista.fpe.builder.steps.Builder;
import com.idealista.fpe.builder.steps.WithDomain;
import com.idealista.fpe.builder.steps.WithLengthRange;
import com.idealista.fpe.builder.steps.WithPseudoRandomFunction;
import com.idealista.fpe.builder.validate.BuildValidator;
import com.idealista.fpe.component.functions.prf.DefaultPseudarandomFunction;
import com.idealista.fpe.component.functions.prf.PseudoRandomFunction;
import com.idealista.fpe.config.Defaults;
import com.idealista.fpe.config.Domain;
import com.idealista.fpe.config.LengthRange;

public class FormatPreservingEncryptionBuilder {

    private static final com.idealista.fpe.algorithm.Cipherer ff1 = new Cipherer();


    public static WithDomain ff1Implementation() {
        return new WithDomainStep(ff1);
    }

    private static class WithDomainStep implements WithDomain {

        private com.idealista.fpe.algorithm.Cipherer cipherer;

        public WithDomainStep(com.idealista.fpe.algorithm.Cipherer cipherer) {
            this.cipherer = cipherer;
        }

        @Override
        public WithPseudoRandomFunction withDomain(Domain domain) {
            return new WithPseudoRandomFunctionStep(cipherer, domain);
        }

        @Override
        public WithPseudoRandomFunction withDefaultDomain() {
            return withDomain(Defaults.DOMAIN);
        }
    }

    private static class WithPseudoRandomFunctionStep implements WithPseudoRandomFunction {

        private com.idealista.fpe.algorithm.Cipherer cipherer;
        private final Domain selectedDomain;

        private WithPseudoRandomFunctionStep(com.idealista.fpe.algorithm.Cipherer cipherer, Domain selectedDomain) {
            this.cipherer = cipherer;
            this.selectedDomain = selectedDomain;
        }

        @Override
        public WithLengthRange withPseudoRandomFunction(PseudoRandomFunction pseudoRandomFunction) {
            return new WithLengthRangeStep(cipherer, selectedDomain, pseudoRandomFunction);
        }

        @Override
        public WithLengthRange withDefaultPseudoRandomFunction(byte[] key) {
            return withPseudoRandomFunction(new DefaultPseudarandomFunction(key));
        }
    }

    private static class WithLengthRangeStep implements WithLengthRange {
        private final com.idealista.fpe.algorithm.Cipherer cipherer;
        private final Domain selectedDomain;
        private final PseudoRandomFunction selectedPRF;

        private WithLengthRangeStep(com.idealista.fpe.algorithm.Cipherer cipherer, Domain selectedDomain, PseudoRandomFunction selectedPRF) {
            this.cipherer = cipherer;
            this.selectedDomain = selectedDomain;
            this.selectedPRF = selectedPRF;
        }


        @Override
        public Builder withLengthRange(LengthRange lengthRange) {
            return new TheBuilder(cipherer, selectedDomain, selectedPRF, lengthRange);
        }

        @Override
        public Builder withDefaultLengthRange() {
            return withLengthRange(Defaults.LENGTH_RANGE);
        }
    }

    private static class TheBuilder implements Builder{
        private final com.idealista.fpe.algorithm.Cipherer cipherer;
        private final Domain selectedDomain;
        private final PseudoRandomFunction selectedPRF;
        private final LengthRange lengthRange;

        private TheBuilder(com.idealista.fpe.algorithm.Cipherer cipherer, Domain selectedDomain, PseudoRandomFunction selectedPRF, LengthRange lengthRange) {
            this.cipherer = cipherer;
            this.selectedDomain = selectedDomain;
            this.selectedPRF = selectedPRF;
            this.lengthRange = lengthRange;
        }

        @Override
        public FormatPreservingEncryption build() {
            new BuildValidator(selectedDomain.alphabet().radix(), lengthRange.min(), lengthRange.max()).validate();
            return new FormatPreservingEncryption(cipherer, selectedDomain, selectedPRF, lengthRange);
        }
    }
}
