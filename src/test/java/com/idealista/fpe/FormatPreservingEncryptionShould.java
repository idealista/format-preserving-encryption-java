package com.idealista.fpe;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

import com.idealista.fpe.builder.FormatPreservingEncryptionBuilder;
import com.idealista.fpe.config.Defaults;
import com.idealista.fpe.config.LengthRange;
import com.idealista.fpe.config.basic.BasicAlphabet;

public class FormatPreservingEncryptionShould {

    private byte[] anyKey = new byte[]{
            (byte) 0x00, (byte) 0x00, (byte) 0x00, (byte) 0x00,
            (byte) 0x01, (byte) 0x01, (byte) 0x01, (byte) 0x01,
            (byte) 0x02, (byte) 0x02, (byte) 0x02, (byte) 0x02,
            (byte) 0x03, (byte) 0x03, (byte) 0x03, (byte) 0x03
    };

    @Test
    public void with_default_parameters_given_a_valid_domain_text_and_an_empty_tweak_should_return_a_valid_cipher_text () {
        FormatPreservingEncryption formatPreservingEncryption = defaultFormatPreservingEncryption();

        String anyPlainTextOfDefaultDomain = "abcdefgsffsdfe";

        String cipherText = formatPreservingEncryption.encrypt(anyPlainTextOfDefaultDomain, new byte[0]);

        assertThat(cipherText).hasSameSizeAs(anyPlainTextOfDefaultDomain);
        assertThat(new BasicAlphabet().availableCharacters()).contains(cipherText.toCharArray());
        String reverse = formatPreservingEncryption.decrypt(cipherText, new byte[0]);
        assertThat(reverse).isEqualTo(anyPlainTextOfDefaultDomain);
    }

    @Test
    public void with_default_parameters_given_a_valid_domain_text_and_a_valid_tweak_should_return_a_valid_cipher_text () {
        FormatPreservingEncryption formatPreservingEncryption = defaultFormatPreservingEncryption();

        String anyPlainTextOfDefaultDomain = "abcdefgsffsdfe";
        byte[] anyTweak = new byte[]{
                (byte) 0x01, (byte) 0x03, (byte) 0x02, (byte) 0x04
        };

        String cipherText = formatPreservingEncryption.encrypt(anyPlainTextOfDefaultDomain, anyTweak);

        assertThat(cipherText).hasSameSizeAs(anyPlainTextOfDefaultDomain);
        assertThat(new BasicAlphabet().availableCharacters()).contains(cipherText.toCharArray());
        String reverse = formatPreservingEncryption.decrypt(cipherText, anyTweak);
        assertThat(reverse).isEqualTo(anyPlainTextOfDefaultDomain);
    }

    @Test
    public void with_default_parameters_given_a_valid_domain_text_and_two_different_tweaks_should_return_a_invalid_cipher_text() {
        FormatPreservingEncryption formatPreservingEncryption = defaultFormatPreservingEncryption();

        String anyPlainTextOfDefaultDomain = "abcdefgsffsdfe";
        byte[] oneTweak = new byte[]{
                (byte) 0x01, (byte) 0x03, (byte) 0x02, (byte) 0x04
        };

        byte[] otherTweak = new byte[]{
                (byte) 0x01, (byte) 0x02, (byte) 0x03, (byte) 0x04
        };

        String cipherText = formatPreservingEncryption.encrypt(anyPlainTextOfDefaultDomain, oneTweak);

        assertThat(cipherText).hasSameSizeAs(anyPlainTextOfDefaultDomain);
        assertThat(new BasicAlphabet().availableCharacters()).contains(cipherText.toCharArray());
        String reverse = formatPreservingEncryption.decrypt(cipherText, otherTweak);
        assertThat(reverse).isNotEqualTo(anyPlainTextOfDefaultDomain);
    }

    @Test
    public void with_default_parameters_given_a_very_short_valid_domain_text_and_a_tweak_should_throws_an_exception() {
        FormatPreservingEncryption formatPreservingEncryption = defaultFormatPreservingEncryption();

        String tooShortDomainText = "a";
        byte[] oneTweak = new byte[]{
                (byte) 0x01, (byte) 0x03, (byte) 0x02, (byte) 0x04
        };

        try{
            String cipherText = formatPreservingEncryption.encrypt(tooShortDomainText, oneTweak);
            assertThat(cipherText).isBlank();
        }catch (IllegalArgumentException exception) {
            assertThat(exception).hasMessageContaining(FormatPreservingEncryption.INVALID_SIZE);
        }
        try{
            String plainText = formatPreservingEncryption.decrypt(tooShortDomainText, oneTweak);
            assertThat(plainText).isBlank();
        }catch (IllegalArgumentException exception) {
            assertThat(exception).hasMessageContaining(FormatPreservingEncryption.INVALID_SIZE);
        }
    }

    @Test
    public void with_a_low_max_length_given_a_large_valid_domain_text_and_a_tweak_should_throws_an_exception() {
        int lowMaxLength = 7;
        FormatPreservingEncryption formatPreservingEncryption = FormatPreservingEncryptionBuilder.ff1Implementation()
                .withDefaultDomain()
                .withDefaultPseudoRandomFunction(anyKey)
                .withLengthRange(new LengthRange(Defaults.DEFAULT_MIN_LENGTH, lowMaxLength))
                .build();

        StringBuilder builder = new StringBuilder(lowMaxLength * 2);
        for (int i = 0; i < lowMaxLength * 2; i++) {
            builder.append("a");
        }
        String largeText = builder.toString();

        byte[] oneTweak = new byte[]{
                (byte) 0x01, (byte) 0x03, (byte) 0x02, (byte) 0x04
        };

        try{
            String cipherText = formatPreservingEncryption.encrypt(largeText, oneTweak);
            assertThat(cipherText).isBlank();
        }catch (IllegalArgumentException exception) {
            assertThat(exception).hasMessageContaining(FormatPreservingEncryption.INVALID_SIZE);
        }
        try{
            String plainText = formatPreservingEncryption.decrypt(largeText, oneTweak);
            assertThat(plainText).isBlank();
        }catch (IllegalArgumentException exception) {
            assertThat(exception).hasMessageContaining(FormatPreservingEncryption.INVALID_SIZE);
        }
    }

    @Test
    public void given_null_input_should_throws_an_exception() {
        FormatPreservingEncryption formatPreservingEncryption = defaultFormatPreservingEncryption();

        try{
            String cipherText = formatPreservingEncryption.encrypt(null, null);
            assertThat(cipherText).isBlank();
        }catch (IllegalArgumentException exception) {
            assertThat(exception).hasMessageContaining(FormatPreservingEncryption.NULL_INPUT);
        }

        try{
            String plainText = formatPreservingEncryption.decrypt(null, null);
            assertThat(plainText).isBlank();
        }catch (IllegalArgumentException exception) {
            assertThat(exception).hasMessageContaining(FormatPreservingEncryption.NULL_INPUT);
        }
    }

    private FormatPreservingEncryption defaultFormatPreservingEncryption() {
        return FormatPreservingEncryptionBuilder.ff1Implementation()
                    .withDefaultDomain()
                    .withDefaultPseudoRandomFunction(anyKey)
                    .withDefaultLengthRange()
                    .build();
    }

}