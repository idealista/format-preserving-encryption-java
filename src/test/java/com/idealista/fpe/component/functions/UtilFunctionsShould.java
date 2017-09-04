package com.idealista.fpe.component.functions;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;

public class UtilFunctionsShould {

    @Test
    public void log_given_an_integer_x_grater_than_zero_return_log_base_two_of_x () {
        assertThat(UtilFunctions.log(2), is(1));
        assertThat(UtilFunctions.log(4), is(2));
        assertThat(UtilFunctions.log(8), is(3));
    }

    @Test (expected = IllegalArgumentException.class)
    public void log_given_an_integer_x_lower_than_zero_throws_an_exception () {
        UtilFunctions.log(-4);
    }
}
