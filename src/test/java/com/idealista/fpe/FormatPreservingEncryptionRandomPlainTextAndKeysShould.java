package com.idealista.fpe;

import static org.assertj.core.api.Assertions.assertThat;

import javax.crypto.KeyGenerator;

import org.junit.Test;

import com.idealista.fpe.builder.FormatPreservingEncryptionBuilder;
import com.idealista.fpe.config.Defaults;

public class FormatPreservingEncryptionRandomPlainTextAndKeysShould {


    private Integer radix = 0;
    private byte[] key = new byte[0];
    private byte[] tweak = new byte[] {(byte) 0xEf5, (byte) 0x03, (byte) 0xF9};
    private String input = null;
    private String values = "";


    @Test
    public void given_a_plain_text_return_the_cipher_text () throws Exception {
        for (int i=0; i<100; i++) {
            generateValues();
            FormatPreservingEncryption formatPreservingEncryption = defaultFormatPreservingEncryption();
            String cipherText = formatPreservingEncryption.encrypt(input, tweak);
            assertThat(input).as(values).hasSameSizeAs(cipherText);
            assertThat(input).as(values).isNotEqualTo(cipherText);
            assertThat(input).as(values).isEqualTo(formatPreservingEncryption.decrypt(cipherText, tweak));
        }

    }

    @Test
    public void given_a_cipher_text_return_the_plain_text () throws Exception {
        for (int i=0; i<100; i++) {
            generateValues();
            FormatPreservingEncryption formatPreservingEncryption = defaultFormatPreservingEncryption();
            String plainText = formatPreservingEncryption.decrypt(input, tweak);
            assertThat(input).as(values).hasSameSizeAs(plainText);
            assertThat(input).as(values).isNotEqualTo(plainText);
            assertThat(input).as(values).isEqualTo(formatPreservingEncryption.encrypt(plainText, tweak));
        }

    }

    private void generateValues() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(RandomValuesProvider.getRandomKeyLength());
        radix = Defaults.ALPHABET.radix();
        key = keyGenerator.generateKey().getEncoded();
        input = RandomValuesProvider.randomPlainTextOfBasicDomain();
        values = RandomValuesProvider.valuesAsString(input, key, radix);
    }

    private FormatPreservingEncryption defaultFormatPreservingEncryption() {
        return FormatPreservingEncryptionBuilder.ff1Implementation()
                .withDefaultDomain()
                .withDefaultPseudoRandomFunction(key)
                .withDefaultLengthRange()
                .build();
    }


}