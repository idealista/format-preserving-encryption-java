package com.idealista.fpe.component.functions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.math.BigInteger;

import org.junit.Test;

public class ComponentFunctionNumRadixX {

    @Test
    public void given_plain_data_return_the_equivalent_number_in_base_radix_when_evaluated_in_decreasing_order () {
        int[] plainData = {0, 0, 0, 1, 1, 0, 1, 0};
        BigInteger expectedResult = BigInteger.valueOf(755);
        Integer radix = 5;
        BigInteger result = ComponentFunctions.num(plainData, radix);
        assertThat(result, is(expectedResult));
    }


    @Test(expected = IllegalArgumentException.class)
    public void given_plain_data_with_a_number_bigger_than_radix_throw_an_exception () {
        int[] plainData = {0, 9, 0, 1, 1, 0, 1, 0};
        Integer radix = 5;
        ComponentFunctions.num(plainData, radix);
    }


}