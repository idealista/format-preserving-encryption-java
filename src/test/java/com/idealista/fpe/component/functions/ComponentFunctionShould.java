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
    public void num_x_given_byte_integer_plain_data_return_the_integer_x_where_x_to_plain_data_length_is_equal_to_plain_data () {
        byte[][] samples = {
                {(byte) 0x00},
                {(byte) 0x02},
                {(byte) 0x80},
                {(byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x03},
                {(byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x02},
                {(byte) 0x03, (byte) 0x00, (byte) 0x00, (byte) 0x01},
                {(byte) 0x04, (byte) 0x00, (byte) 0x00, (byte) 0x00},
                {(byte) 0x05, (byte) 0x00, (byte) 0x00, (byte) 0x01},
                {(byte) 0x06, (byte) 0x00, (byte) 0x00, (byte) 0x00},
                {(byte) 0x07, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF},
                {(byte) 0x08, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF, (byte) 0xFF}
        };
        for (byte[] plainData : samples) {
            byte[] result = ComponentFunctions.num(plainData).pow(plainData.length).toByteArray();
            // see https://stackoverflow.com/questions/4407779/biginteger-to-byte
            if(result.length > 1)
                result  = Arrays.copyOfRange(result, 1, result.length);
            assertThat(result, is(plainData));
        }
    }




}