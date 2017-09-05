package com.idealista.fpe.component.functions;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;

import org.junit.Test;

public class UtilFunctionsShould {

    @Test
    public void log_given_an_integer_x_grater_than_zero_return_log_base_two_of_x () {
        assertThat(UtilFunctions.log(2), closeTo(1, 0.01));
        assertThat(UtilFunctions.log(4), closeTo(2, 0.01));
        assertThat(UtilFunctions.log(8), closeTo(3, 0.01));
    }

    @Test (expected = IllegalArgumentException.class)
    public void log_given_an_integer_x_lower_than_zero_throws_an_exception () {
        UtilFunctions.log(-4);
    }
}
