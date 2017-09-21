package com.idealista.fpe.builder;

import com.idealista.fpe.FormatPreservingEncryption;
import com.idealista.fpe.algorithm.ff1.Cipherer;
import com.idealista.fpe.builder.steps.Builder;
import com.idealista.fpe.builder.steps.WithDomain;
import com.idealista.fpe.builder.steps.WithLengthRange;
import com.idealista.fpe.builder.steps.WithPseudoRandomFunction;
import com.idealista.fpe.builder.validate.BuildValidator;
import com.idealista.fpe.component.functions.prf.DefaultPseudarandomFunction;
import com.idealista.fpe.component.functions.prf.PseudorandomFunction;
import com.idealista.fpe.config.Defaults;
import com.idealista.fpe.config.Domain;
import com.idealista.fpe.config.LengthRange;

public class FormatPreservingEncryptionBuilder {

    private static final com.idealista.fpe.algorithm.Cipherer ff1 = new Cipherer();


    public static WithDomain ff1Implementation() {
        return new WithDomainStep();
    }

    private static class WithDomainStep implements WithDomain {

        @Override
        public WithPseudoRandomFunction withDomain(Domain domain) {
            return new WithPseudoRandomFunctionStep(domain);
        }

        @Override
        public WithPseudoRandomFunction withDefaultDomain() {
            return new WithPseudoRandomFunctionStep(Defaults.DOMAIN);
        }
    }

    private static class WithPseudoRandomFunctionStep implements WithPseudoRandomFunction {

        private final Domain selectedDomain;

        private WithPseudoRandomFunctionStep(Domain selectedDomain) {
            this.selectedDomain = selectedDomain;
        }

        @Override
        public WithLengthRange withPseudoRandomFunction(PseudorandomFunction pseudoRandomFunction) {
            return new WithLengthRangeStep(selectedDomain, pseudoRandomFunction);
        }

        @Override
        public WithLengthRange withDefaultPseudoRandomFunction(byte[] key) {
            return new WithLengthRangeStep(selectedDomain, new DefaultPseudarandomFunction(key));
        }
    }

    private static class WithLengthRangeStep implements WithLengthRange {
        private final Domain selectedDomain;
        private final PseudorandomFunction selectedPRF;

        private WithLengthRangeStep(Domain selectedDomain, PseudorandomFunction selectedPRF) {
            this.selectedDomain = selectedDomain;
            this.selectedPRF = selectedPRF;
        }


        @Override
        public Builder withLengthRange(LengthRange lengthRange) {
            return new TheBuilder(selectedDomain, selectedPRF, lengthRange);
        }

        @Override
        public Builder withDefaultLengthRange() {
            return withLengthRange(Defaults.LENGTH_RANGE);
        }
    }

    private static class TheBuilder implements Builder{
        private final Domain selectedDomain;
        private final PseudorandomFunction selectedPRF;
        private final LengthRange lengthRange;

        private TheBuilder(Domain selectedDomain, PseudorandomFunction selectedPRF, LengthRange lengthRange) {
            this.selectedDomain = selectedDomain;
            this.selectedPRF = selectedPRF;
            this.lengthRange = lengthRange;
        }

        @Override
        public FormatPreservingEncryption build() {
            new BuildValidator(selectedDomain.alphabet().radix(), lengthRange.min(), lengthRange.max()).validate();
            return new FormatPreservingEncryption();
        }
    }
}
