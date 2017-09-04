package com.idealista.fpe.component.functions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import java.math.BigInteger;
import java.util.Arrays;

import org.junit.Test;

public class ComponentFunctionShould {

    //Test cases: http://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-38G.pdf # Sec4.2

    @Test
    public void num_radix_x_given_plain_data_return_the_equivalent_number_in_base_radix_when_evaluated_in_decreasing_order () {
        int[] plainData = {0, 0, 0, 1, 1, 0, 1, 0};
        BigInteger expectedResult = BigInteger.valueOf(755);
        Integer radix = 5;
        BigInteger result = ComponentFunctions.num(plainData, radix);
        assertThat(result, is(expectedResult));
    }

    @Test
    public void num_x_given_byte_intenger_plain_data_return_the_integer_x_where_x_to_plain_data_lenth_is_equal_to_plain_data () {
        BigInteger expectedResult = BigInteger.valueOf(128);
        byte[] plainData = {(byte) 0x80 };
        assertThat(ComponentFunctions.num(plainData) , is(expectedResult));
        byte[] checkResult = ComponentFunctions.num(plainData).pow(plainData.length).toByteArray();
        // see https://stackoverflow.com/questions/4407779/biginteger-to-byte
        byte[] checkResultWithoutByteSing = Arrays.copyOfRange(checkResult, 1, checkResult.length);
        assertThat(checkResultWithoutByteSing, is(plainData));
    }




}