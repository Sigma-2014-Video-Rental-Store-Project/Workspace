package ua.nure.sigma.store.validator.film;

import org.junit.Test;
import ua.nure.sigma.store.validator.Condition;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FilmDescriptionConditionTest {

    @Test
    public void testValidateEmptyDescription(){
        final String expected = FilmDescriptionCondition.EMPTY_DESCRIPTION;
        final String toValidate = "";

        // Set up target object.
        Condition condition = new FilmDescriptionCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateTooBigDescription(){
        final String expected = FilmDescriptionCondition.TOO_BIG_DESCRIPTION;
        final StringBuilder sb = new StringBuilder();
        for(int i=0; i<100; ++i){
            sb.append("string");
        }
        final String toValidate = sb.toString();

        // Set up target object.
        Condition condition = new FilmDescriptionCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateDescriptionPass(){
        final String toValidate = "Description";

        // Set up target object.
        Condition condition = new FilmDescriptionCondition();

        // Target assertion.
        assertNull(condition.validate(toValidate));
    }
}