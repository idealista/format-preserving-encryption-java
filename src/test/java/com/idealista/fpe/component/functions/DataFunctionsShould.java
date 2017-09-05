package com.idealista.fpe.component.functions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

import java.awt.image.BufferedImage;
import java.math.BigInteger;

import javax.xml.crypto.Data;

import org.hamcrest.core.Is;
import org.junit.Test;

public class DataFunctionsShould {

    @Test
    public void log_given_an_integer_x_grater_than_zero_return_log_base_two_of_x () {
        assertThat(DataFunctions.log(2), closeTo(1, 0.01));
        assertThat(DataFunctions.log(4), closeTo(2, 0.01));
        assertThat(DataFunctions.log(8), closeTo(3, 0.01));
    }

    @Test (expected = IllegalArgumentException.class)
    public void log_given_an_integer_x_lower_than_zero_throws_an_exception () {
        DataFunctions.log(-4);
    }

    @Test
    public void  concatenate_given_two_array_should_return_one_with_the_elements_of_inputs () {
        int[] left = {1, 2};
        int[] right = {3, 4};
        int [] result = DataFunctions.concatenate(left, right);
        assertThat(result[0], is(left[0]));
        assertThat(result[1], is(left[1]));
        assertThat(result[2], is(right[0]));
        assertThat(result[3], is(right[1]));
    }

    @Test
    public void xor_given_two_bit_string_return_a_bit_string_with_same_length_after_applied_individual_modular_add (){
        byte[] left = {(byte) 0x01, (byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x01};
        byte[] right = {(byte) 0x01, (byte) 0x00, (byte) 0x01, (byte) 0x00, (byte) 0x01};
        byte[] result = DataFunctions.xor(left, right);
        assertThat(result, is(new byte[]{(byte) 0x00, (byte) 0x00, (byte) 0x01, (byte) 0x01, (byte) 0x00}));
    }

    @Test
    public void number_as_array_of_bytes_given_a_number_and_a_length_return_a_byte_array_of_length_that_represent_number (){
        Integer number = 5;
        BigInteger bigNumber = BigInteger.valueOf(5);
        byte[] numberAsByte = new byte[] {(byte) 0x00, (byte) 0x05};
        assertThat(numberAsByte, is(DataFunctions.numberAsArrayOfBytes(number, 2).raw()));
        assertThat(numberAsByte, is(DataFunctions.numberAsArrayOfBytes(bigNumber, 2).raw()));
    }

    @Test
    public void mod_given_a_number_and_a_base_return_representation_of_number_as_base (){
        assertThat(0, is(DataFunctions.mod(125, 5)));
        assertThat(3, is(DataFunctions.mod(3, 5)));
    }

    @Test (expected = ArithmeticException.class)
    public void mod_given_a_negative_base_throw_an_exception (){
        DataFunctions.mod(125, -5);
    }
}
