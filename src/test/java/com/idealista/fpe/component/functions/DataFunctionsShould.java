package com.idealista.fpe.component.functions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

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
}
