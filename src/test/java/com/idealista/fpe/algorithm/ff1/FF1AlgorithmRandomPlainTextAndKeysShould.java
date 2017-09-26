package com.idealista.fpe.algorithm.ff1;

import static org.assertj.core.api.Assertions.assertThat;

import javax.crypto.KeyGenerator;

import org.junit.Test;

import com.idealista.fpe.RandomValuesProvider;
import com.idealista.fpe.component.functions.prf.DefaultPseudoRandomFunction;

public class FF1AlgorithmRandomPlainTextAndKeysShould {


    private Integer radix = 0;
    private byte[] key = new byte[0];
    private byte[] tweak = new byte[] {(byte) 0xEf5, (byte) 0x03, (byte) 0xF9};
    private int[] input = new int[0];
    private String values = "";


    @Test
    public void given_a_plain_text_return_the_cipher_text () throws Exception {
        for (int i=0; i<100; i++) {
            generateValues();
            int[] cipherText = FF1Algorithm.encrypt(input, radix, tweak, new DefaultPseudoRandomFunction(key));
            assertThat(input.length).as(values).isEqualTo(cipherText.length);
            assertThat(input).as(values).isNotEqualTo(cipherText);
            assertThat(input).as(values).isEqualTo(FF1Algorithm.decrypt(cipherText, radix, tweak, new DefaultPseudoRandomFunction(key)));
        }

    }

    @Test
    public void given_a_cipher_text_return_the_plain_text () throws Exception {
        for (int i=0; i<100; i++) {
            generateValues();
            int[] plainText = FF1Algorithm.decrypt(input, radix, tweak, new DefaultPseudoRandomFunction(key));
            assertThat(input.length).as(values).isEqualTo(plainText.length);
            assertThat(input).as(values).isNotEqualTo(plainText);
            assertThat(input).as(values).isEqualTo(FF1Algorithm.encrypt(plainText, radix, tweak, new DefaultPseudoRandomFunction(key)));
        }

    }

    private void generateValues() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(RandomValuesProvider.getRandomKeyLength());
        key = keyGenerator.generateKey().getEncoded();
        radix = RandomValuesProvider.getRandomRadix();
        input = RandomValuesProvider.randomPlainText(radix);
        values = RandomValuesProvider.valuesAsString(input, key, radix);
    }


}