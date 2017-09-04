package com.idealista.fpe.ff1;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class FF1AlgorithmShould {

    // Test cases : http://csrc.nist.gov/groups/ST/toolkit/documents/Examples/FF1samples.pdf

    @Test // #2
    public void given_a_plain_text_return_the_cipher_text () {
        Integer radix = 10;
        byte[] key = { (byte) 0x2B, (byte) 0x7E, (byte) 0x15, (byte) 0x16, (byte) 0x28, (byte) 0xAE, (byte) 0xD2,
                (byte) 0xA6, (byte) 0xAB, (byte) 0xF7, (byte) 0x15, (byte) 0x88, (byte) 0x09, (byte) 0xCF, (byte) 0x4F,
                (byte) 0x3C };
        byte[] tweak = {
                (byte) 0x39, (byte) 0x38, (byte) 0x37, (byte) 0x36, (byte) 0x35, (byte) 0x34, (byte) 0x33, (byte) 0x32, (byte) 0x31, (byte) 0x30
        };

        int[] plainText = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] expectedCipherText = {6, 1, 2, 4, 2, 0, 0, 7, 7, 3};

        int[] cipherText = FF1Algorithm.encrypt(plainText, radix, key, tweak);
        assertThat(cipherText, is(expectedCipherText));

    }

}