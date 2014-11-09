package ua.nure.sigma.store.validator.film;

import org.junit.Test;
import ua.nure.sigma.store.validator.Condition;

import java.util.Calendar;
import java.util.GregorianCalendar;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class FilmYearConditionTest {

    @Test
    public void testValidateEmptyYear() {
        final String expected = FilmYearCondition.EMPTY_YEAR;
        final String toValidate = "";

        // Set up target object.
        Condition condition = new FilmYearCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateSmallYear() {
        final String expected = FilmYearCondition.SMALL_YEAR;
        final String toValidate = "1712";

        // Set up target object.
        Condition condition = new FilmYearCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateTooBigYear() {
        final String expected = FilmYearCondition.TOO_BIG_YEAR;
        final String toValidate = Integer.toString(new GregorianCalendar().get(Calendar.YEAR) + 1);
        ;

        // Set up target object.
        Condition condition = new FilmYearCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateIncorrectYear() {
        final String expected = FilmYearCondition.INCORRECT_YEAR;
        final String toValidate = "Fail";

        // Set up target object.
        Condition condition = new FilmYearCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateYearPass() {
        final String toValidate = Integer.toString(new GregorianCalendar().get(Calendar.YEAR));

        // Set up target object.
        Condition condition = new FilmYearCondition();

        // Target assertion.
        assertNull(condition.validate(toValidate));
    }
}