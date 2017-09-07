package com.idealista.fpe.builder;

import org.junit.Test;

public class BuilValidatorShould {
    /*
     - null protection for all inputs
     - key is an AES Key, must be 16, 24 or 32 bytes length
     -  tweak should be lower that maxlen
     -  n ∈ [minlen .. maxlen];
     -  0 < plainText[i] <= radix
     */

    @Test (expected = IllegalArgumentException.class)
    public void throws_an_exception_when_radix_is_less_than_two () {
        BuildValidator.radix(1);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throws_an_exception_when_radix_is_greater_than_two_to_sixteen () {
        BuildValidator.radix((int) (Math.pow(2d, 16d) * 2));
    }

    @Test (expected = IllegalArgumentException.class)
    public void throws_an_exception_when_radix_to_minLength_is_greater_than_one_hundred () {
        BuildValidator.radixToMinLength(11, 2);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throws_an_exception_when_min_length_is_less_than_two () {
        int anyNumber = 1234;
        BuildValidator.textSize(1, anyNumber);
    }

    @Test (expected = IllegalArgumentException.class)
    public void throws_an_exception_when_min_length_is_greater_or_equal_than_maxLength () {
        int anyLoweNumber = 1234;
        int minLength = anyLoweNumber + 10;
        BuildValidator.textSize(minLength, anyLoweNumber);
    }

}
