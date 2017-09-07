package com.idealista.fpe.builder;

import org.junit.Test;

public class BuildValidatorShould {


    @Test (expected = IllegalArgumentException.class)
    public void throws_an_exception_when_radix_is_less_than_two () {
        new BuildValidator(1, 0, 0, new byte[0]).radix();
    }

    @Test (expected = IllegalArgumentException.class)
    public void throws_an_exception_when_radix_is_greater_than_two_to_sixteen () {
        new BuildValidator((int) (Math.pow(2d, 16d) * 2), 0, 0, new byte[0]).radix();
    }

    @Test (expected = IllegalArgumentException.class)
    public void throws_an_exception_when_radix_to_minLength_is_greater_than_one_hundred () {
        new BuildValidator(11, 2, 0, new byte[0]).radixToMinLength();
    }

    @Test (expected = IllegalArgumentException.class)
    public void throws_an_exception_when_min_length_is_less_than_two () {
        int anyNumber = 1234;
        new BuildValidator(1, 1, anyNumber, new byte[0]).textSize();
    }

    @Test (expected = IllegalArgumentException.class)
    public void throws_an_exception_when_min_length_is_greater_or_equal_than_maxLength () {
        int anyLoweNumber = 1234;
        int minLength = anyLoweNumber + 10;
        new BuildValidator(1, minLength, anyLoweNumber, new byte[0]).textSize();
    }

    @Test (expected = IllegalArgumentException.class)
    public void throws_an_exception_when_tweak_length_is_greater_than_maxLength () {
        int aMaxLength = 10;
        byte[] anyLargeTweak = new byte[aMaxLength * 2];
        new BuildValidator(1, 0, aMaxLength, anyLargeTweak).tweakSize();
    }

}
