package com.idealista.fpe.builder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.idealista.fpe.FormatPreservingEncryption;
import com.idealista.fpe.component.functions.prf.DefaultPseudarandomFunction;
import com.idealista.fpe.config.basic.BasicAlphabetDomain;

public class FormatPreservingEncryptionBuilderShould {

    byte[] anyKey = new byte[]{
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00
    };

    @Test
    public void return_an_instance_with_given_configuration () {
        FormatPreservingEncryption formatPreservingEncryption = FormatPreservingEncryptionBuilder
                .ff1Implementation()
                .withDomain(new BasicAlphabetDomain())
                .withPseudoRandomFunction(new DefaultPseudarandomFunction(anyKey))
                .withLengthRange(1, 2)
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
