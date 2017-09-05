package com.idealista.fpe.ff1;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Random;

import javax.crypto.KeyGenerator;

import org.junit.Before;
import org.junit.Test;

import com.idealista.fpe.component.functions.prf.DefaultPseudarandomFunction;

public class FF1AlgorithmRandomPlainTextAndKeys {


    private Integer radix = 0;
    private byte[] key = new byte[0];
    private byte[] tweak = new byte[0];
    private int[] input = new int[0];

    @Before
    public void setUp () throws Exception {
        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(getRandomKeyLength());
        key = keyGenerator.generateKey().getEncoded();
        tweak = keyGenerator.generateKey().getEncoded();
        radix = getRandomRadix();
        input = randomPlainText();
    }

    @Test
    public void given_a_plain_text_return_the_cipher_text () throws Exception {
        int[] cipherText = FF1Algorithm.encrypt(input, radix, tweak, new DefaultPseudarandomFunction(key));
        assertThat(input.length, is(cipherText.length));
        assertThat(input, is(not(cipherText)));
        assertThat(input, is(FF1Algorithm.decrypt(cipherText, radix, tweak, new DefaultPseudarandomFunction(key))));

    }

    @Test
    public void given_a_cipher_text_return_the_plain_text () throws Exception {
        int[] plainText = FF1Algorithm.decrypt(input, radix, tweak, new DefaultPseudarandomFunction(key));
        assertThat(input.length, is(plainText.length));
        assertThat(input, is(not(plainText)));
        assertThat(input, is(FF1Algorithm.encrypt(plainText, radix, tweak, new DefaultPseudarandomFunction(key))));

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
        int[] anySizes = {1, 5, 14, 25};
        int rnd = new Random().nextInt(anySizes.length);
        return anySizes[rnd];
    }


    private int getRandomRadix() {
        int[] anySizes = {10, 20, 30, 40, 50, 60, 70, 80, 90, 100};
        int rnd = new Random().nextInt(anySizes.length);
        return anySizes[rnd];
    }


}