package com.idealista.fpe.builder;

import com.idealista.fpe.FormatPreservingEncryption;
import com.idealista.fpe.algorithm.Cipher;
import com.idealista.fpe.builder.steps.Builder;
import com.idealista.fpe.builder.steps.WithDomain;
import com.idealista.fpe.builder.steps.WithLengthRange;
import com.idealista.fpe.builder.steps.WithPseudoRandomFunction;
import com.idealista.fpe.builder.validate.BuildValidator;
import com.idealista.fpe.component.functions.prf.DefaultPseudoRandomFunction;
import com.idealista.fpe.component.functions.prf.PseudoRandomFunction;
import com.idealista.fpe.config.Defaults;
import com.idealista.fpe.config.Domain;
import com.idealista.fpe.config.LengthRange;

public class FormatPreservingEncryptionBuilder {

    private static final String AVOID_INSTANCE_MESSAGE = "use class throw static methods";
    private static final Cipher ff1 = new com.idealista.fpe.algorithm.ff1.Cipher();

    private FormatPreservingEncryptionBuilder () {
        throw  new IllegalArgumentException(AVOID_INSTANCE_MESSAGE);
    }
    public static WithDomain ff1Implementation() {
        return new WithDomainStep(ff1);
    }

    private static class WithDomainStep implements WithDomain {

        private Cipher cipher;

        public WithDomainStep(Cipher cipher) {
            this.cipher = cipher;
        }

        @Override
        public WithPseudoRandomFunction withDomain(Domain domain) {
            return new WithPseudoRandomFunctionStep(cipher, domain);
        }

        @Override
        public WithPseudoRandomFunction withDefaultDomain() {
            return withDomain(Defaults.DOMAIN);
        }
    }

    private static class WithPseudoRandomFunctionStep implements WithPseudoRandomFunction {

        private Cipher cipher;
        private final Domain selectedDomain;

        private WithPseudoRandomFunctionStep(Cipher cipher, Domain selectedDomain) {
            this.cipher = cipher;
            this.selectedDomain = selectedDomain;
        }

        @Override
        public WithLengthRange withPseudoRandomFunction(PseudoRandomFunction pseudoRandomFunction) {
            return new WithLengthRangeStep(cipher, selectedDomain, pseudoRandomFunction);
        }

        @Override
        public WithLengthRange withDefaultPseudoRandomFunction(byte[] key) {
            return withPseudoRandomFunction(new DefaultPseudoRandomFunction(key));
        }
    }

    private static class WithLengthRangeStep implements WithLengthRange {
        private final Cipher cipher;
        private final Domain selectedDomain;
        private final PseudoRandomFunction selectedPRF;

        private WithLengthRangeStep(Cipher cipher, Domain selectedDomain, PseudoRandomFunction selectedPRF) {
            this.cipher = cipher;
            this.selectedDomain = selectedDomain;
            this.selectedPRF = selectedPRF;
        }


        @Override
        public Builder withLengthRange(LengthRange lengthRange) {
            return new TheBuilder(cipher, selectedDomain, selectedPRF, lengthRange);
        }

        @Override
        public Builder withDefaultLengthRange() {
            return withLengthRange(Defaults.LENGTH_RANGE);
        }
    }

    private static class TheBuilder implements Builder{
        private final Cipher cipher;
        private final Domain selectedDomain;
        private final PseudoRandomFunction selectedPRF;
        private final LengthRange lengthRange;

        private TheBuilder(Cipher cipher, Domain selectedDomain, PseudoRandomFunction selectedPRF, LengthRange lengthRange) {
            this.cipher = cipher;
            this.selectedDomain = selectedDomain;
            this.selectedPRF = selectedPRF;
            this.lengthRange = lengthRange;
        }

        @Override
        public FormatPreservingEncryption build() {
            return new FormatPreservingEncryption(cipher, selectedDomain, selectedPRF, lengthRange);
        }
    }
}
