package com.idealista.fpe.builder.validate;

import org.junit.Test;

public class BuildValidatorShould {


    @Test (expected = IllegalArgumentException.class)
    public void throw_an_exception_when_radix_is_less_than_two () {
        new BuildValidator(1, 0, 0).radix();
    }

    @Test (expected = IllegalArgumentException.class)
    public void throw_an_exception_when_radix_is_greater_than_two_to_sixteen () {
        new BuildValidator((int) (Math.pow(2d, 16d) * 2), 0, 0).radix();
    }

    @Test (expected = IllegalArgumentException.class)
    public void throw_an_exception_when_radix_to_minLength_is_greater_than_one_hundred () {
        new BuildValidator(8, 2, 0).radixToMinLength();
    }

    @Test (expected = IllegalArgumentException.class)
    public void throw_an_exception_when_min_length_is_less_than_two () {
        int anyNumber = 1234;
        new BuildValidator(1, 1, anyNumber).textSizeLimits();
    }

    @Test (expected = IllegalArgumentException.class)
    public void throw_an_exception_when_min_length_is_greater_or_equal_than_maxLength () {
        int anyLoweNumber = 1234;
        int minLength = anyLoweNumber + 10;
        new BuildValidator(1, minLength, anyLoweNumber).textSizeLimits();
    }


}
