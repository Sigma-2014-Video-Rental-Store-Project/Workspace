package ua.nure.sigma.store.validator.film;

import org.junit.Test;
import ua.nure.sigma.store.validator.Condition;

import static org.junit.Assert.*;

public class FilmAmountConditionTest {

    @Test
    public void testValidateEmptyAmount(){
        final String expected = FilmAmountCondition.EMPTY_AMOUNT;
        final String toValidate = "";

        // Set up target object.
        Condition condition = new FilmAmountCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateSmallAmount(){
        final String expected = FilmAmountCondition.SMALL_AMOUNT;
        final String toValidate = "-12";

        // Set up target object.
        Condition condition = new FilmAmountCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateIncorrectAmount(){
        final String expected = FilmAmountCondition.INCORRECT_AMOUNT;
        final String toValidate = "Fail";

        // Set up target object.
        Condition condition = new FilmAmountCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateAmountPass(){
        final String toValidate = "12";

        // Set up target object.
        Condition condition = new FilmAmountCondition();

        // Target assertion.
        assertNull(condition.validate(toValidate));
    }
}