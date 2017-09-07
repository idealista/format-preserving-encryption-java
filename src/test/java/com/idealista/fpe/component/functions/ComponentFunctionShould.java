package com.idealista.fpe.component.functions;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigInteger;

import org.junit.Test;

public class ComponentFunctionShould {

    //Test cases: http://nvlpubs.nist.gov/nistpubs/SpecialPublications/NIST.SP.800-38G.pdf # Sec4.2

    @Test
    public void num_radix_x_given_plain_data_return_the_equivalent_number_in_base_radix_when_evaluated_in_decreasing_order() {
        int[] plainData = {0, 0, 0, 1, 1, 0, 1, 0};
        BigInteger expectedResult = BigInteger.valueOf(755);
        Integer radix = 5;
        BigInteger result = ComponentFunctions.num(plainData, radix);
        assertThat(result).isEqualTo(expectedResult);
    }


    @Test
    public void num_x_given_byte_integer_plain_data_return_the_default_radix_plain_data_representation () {
        byte[] toZero = {(byte) 0x00}; // 2 ^ 0
        assertThat(ComponentFunctions.num(toZero)).isEqualTo(BigInteger.ZERO);
        byte[] toFive = {(byte) 0x20}; // 2 ^ 5
        assertThat(ComponentFunctions.num(toFive)).isEqualTo(BigInteger.valueOf(32));
        byte[] toTen = {(byte) 0x04, (byte) 0x00}; // 2 ^ 10
        assertThat(ComponentFunctions.num(toTen)).isEqualTo(BigInteger.valueOf(1024));
        byte[] toFifteen = {(byte) 0x80, (byte) 0x00}; // 2 ^ 15
        assertThat(ComponentFunctions.num(toFifteen)).isEqualTo(BigInteger.valueOf(32768));
        byte[] toTwenty = {(byte) 0x10, (byte) 0x00, (byte) 0x00};// 2 ^ 20
        assertThat(ComponentFunctions.num(toTwenty)).isEqualTo(BigInteger.valueOf(1048576));
        byte[] toTwentyFive = {(byte) 0x02, (byte) 0x00, (byte) 0x00, (byte) 0x00}; // 2 ^ 25
        assertThat(ComponentFunctions.num(toTwentyFive)).isEqualTo(BigInteger.valueOf(33554432));
    }


    @Test
    public void string_radix_m_of_x_given_nonnegative_integer_less_than_radix_to_m_return_x_as_string_of_m_numerals_in_base_radix () {
        BigInteger number = BigInteger.valueOf(559);

        Integer length = 4;
        Integer radix = 12;
        int[] expectedResult = {0, 3, 10, 7};
        assertThat(ComponentFunctions.stringOf(length, radix, number)).isEqualTo(expectedResult);

        Integer otherLength = 5;
        Integer otherRadix = 5;
        int[] otherResult = {0, 4, 2, 1, 4};
        assertThat(ComponentFunctions.stringOf(otherLength, otherRadix, number)).isEqualTo(otherResult);

    }

    @Test (expected = IllegalArgumentException.class)
    public void string_radix_m_of_x_given_nonnegative_integer_bigger_than_radix_to_m_throws_an_exception () {
        Integer length = 4;
        Integer radix = 12;
        int anyNumber = 30;
        ComponentFunctions.stringOf(length, radix, BigInteger.valueOf(radix).pow(length).add(BigInteger.valueOf(anyNumber)));
    }

}