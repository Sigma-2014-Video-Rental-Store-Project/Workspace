package ua.nure.sigma.store.validator.film;

import org.junit.Test;
import ua.nure.sigma.store.validator.Condition;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FilmTitleConditionTest {

    @Test
    public void testValidateEmptyTitle(){
        final String expected = FilmTitleCondition.EMPTY_TITLE;
        final String toValidate = "";

        // Set up target object.
        Condition condition = new FilmTitleCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateTooBigTitle(){
        final String expected = FilmTitleCondition.TOO_BIG_TITLE;
        final StringBuilder sb = new StringBuilder();
        for(int i=0; i<30; ++i){
            sb.append("string");
        }
        final String toValidate = sb.toString();

        // Set up target object.
        Condition condition = new FilmTitleCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateTitlePass(){
        final String toValidate = "Title";

        // Set up target object.
        Condition condition = new FilmTitleCondition();

        // Target assertion.
        assertNull(condition.validate(toValidate));
    }
}