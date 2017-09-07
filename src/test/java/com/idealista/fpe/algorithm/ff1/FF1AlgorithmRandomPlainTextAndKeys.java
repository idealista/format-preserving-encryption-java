package com.idealista.fpe.algorithm.ff1;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Random;

import javax.crypto.KeyGenerator;

import org.junit.Test;

import com.idealista.fpe.component.functions.prf.DefaultPseudarandomFunction;

public class FF1AlgorithmRandomPlainTextAndKeys {


    private Integer radix = 0;
    private byte[] key = new byte[0];
    private byte[] tweak = new byte[] {(byte) 0xEf5, (byte) 0x03, (byte) 0xF9};
    private int[] input = new int[0];
    String values = "";


    @Test
    public void given_a_plain_text_return_the_cipher_text () throws Exception {
        for (int i=0; i<100; i++) {
            generateValues();
            int[] cipherText = FF1Algorithm.encrypt(input, radix, tweak, new DefaultPseudarandomFunction(key));
            assertThat(values, input.length, is(cipherText.length));
            assertThat(values, input, is(not(cipherText)));
            assertThat(values, input, is(FF1Algorithm.decrypt(cipherText, radix, tweak, new DefaultPseudarandomFunction(key))));
        }

    }

    @Test
    public void given_a_cipher_text_return_the_plain_text () throws Exception {
        for (int i=0; i<100; i++) {
            generateValues();
            int[] plainText = FF1Algorithm.decrypt(input, radix, tweak, new DefaultPseudarandomFunction(key));
            assertThat(values, input.length, is(plainText.length));
            assertThat(values, input, is(not(plainText)));
            assertThat(values, input, is(FF1Algorithm.encrypt(plainText, radix, tweak, new DefaultPseudarandomFunction(key))));
        }

    }

    private void generateValues() throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(getRandomKeyLength());
        key = keyGenerator.generateKey().getEncoded();
        radix = getRandomRadix();
        input = randomPlainText();
        values = new StringBuilder()
                .append("input: ").append(asString(input))
                .append("\n")
                .append("key: ").append(asString(key))
                .append("\n")
                .append("radix: ").append(radix).toString();
    }

    private int getRandomKeyLength() {
        int[] keySizes = {128, 192, 256};
        int rnd = new Random().nextInt(keySizes.length);
        return keySizes[rnd];
    }


    private int[] randomPlainText() {
        int [] a = new int[getRandomTextLength()];
        Random randomNumbers = new Random();
        for (int i=0; i<a.length; i++) {
            a[i] = randomNumbers.nextInt(radix);
        }
        return a;
    }

    private int getRandomTextLength() {
        int[] anySizes = {2, 5, 14, 25};
        int rnd = new Random().nextInt(anySizes.length);
        return anySizes[rnd];
    }


    private int getRandomRadix() {
        int[] anySizes = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int rnd = new Random().nextInt(anySizes.length);
        return anySizes[rnd];
    }


    private String asString(int[] value) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (int item : value) {
            builder.append(item).append(", ");
        }
        builder.append("]");
        return builder.toString();
    }
    private String asString(byte[] value) {
        StringBuilder builder = new StringBuilder();
        builder.append("[");
        for (byte item : value) {
            builder.append(item).append(", ");
        }
        builder.append("]");
        return builder.toString();
    }


}