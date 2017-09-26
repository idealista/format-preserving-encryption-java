package com.idealista.fpe.builder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.idealista.fpe.FormatPreservingEncryption;
import com.idealista.fpe.component.functions.prf.DefaultPseudarandomFunction;
import com.idealista.fpe.config.Alphabet;
import com.idealista.fpe.config.LengthRange;
import com.idealista.fpe.config.GenericDomain;
import com.idealista.fpe.config.GenericTransformations;

public class FormatPreservingEncryptionBuilderShould {

    byte[] anyKey = new byte[]{
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
    };

    @Test
    public void return_an_instance_with_given_configuration () {
        // don't use this as production implementation
        Alphabet alphabet = new Alphabet() {

            private char[] chars = {'i', 'd', 'e', 'a', 'l', 'i', 's', 't', 'a'};

            @Override
            public char[] availableCharacters() {
                return chars;
            }

            @Override
            public Integer radix() {
                return chars.length;
            }
        };


        FormatPreservingEncryption formatPreservingEncryption = FormatPreservingEncryptionBuilder
                .ff1Implementation()
                .withDomain(new GenericDomain(alphabet, new GenericTransformations(alphabet.availableCharacters()), new GenericTransformations(alphabet.availableCharacters())))
                .withPseudoRandomFunction(new DefaultPseudarandomFunction(anyKey))
                .withLengthRange(new LengthRange(4, 20))
                .build();

        assertThat(formatPreservingEncryption).isNotNull();

    }

    @Test
    public void return_an_instance_with_default_configuration () {
        FormatPreservingEncryption formatPreservingEncryption = FormatPreservingEncryptionBuilder
                .ff1Implementation()
                .withDefaultDomain()
                .withDefaultPseudoRandomFunction(anyKey)
                .withDefaultLengthRange()
                .build();

        assertThat(formatPreservingEncryption).isNotNull();

    }


}
