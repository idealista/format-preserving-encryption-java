package com.idealista.fpe.builder;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;

public class BuilValidatorShould {
    /*
     - null protection for all inputs
     - radix ∈ [ 2 .. 2 ^ 16 ] && radix ^ minlen >= 100
     - 2 <= minlen < maxlen <= 2^32
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


}
