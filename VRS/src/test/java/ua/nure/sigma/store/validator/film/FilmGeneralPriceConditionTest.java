package ua.nure.sigma.store.validator.film;

import org.junit.Test;
import ua.nure.sigma.store.validator.Condition;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FilmGeneralPriceConditionTest {

    @Test
    public void testValidateEmptyGeneralPrice(){
        final String expected = FilmGeneralPriceCondition.EMPTY_GENERAL_PRICE;
        final String toValidate = "";

        // Set up target object.
        Condition condition = new FilmGeneralPriceCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateSmallGeneralPrice(){
        final String expected = FilmGeneralPriceCondition.SMALL_GENERAL_PRICE;
        final String toValidate = "-12";

        // Set up target object.
        Condition condition = new FilmGeneralPriceCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateIncorrectGeneralPrice(){
        final String expected = FilmGeneralPriceCondition.INCORRECT_GENERAL_PRICE;
        final String toValidate = "Fail";

        // Set up target object.
        Condition condition = new FilmGeneralPriceCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateGeneralPricePass(){
        final String toValidate = "12";

        // Set up target object.
        Condition condition = new FilmGeneralPriceCondition();

        // Target assertion.
        assertNull(condition.validate(toValidate));
    }
}