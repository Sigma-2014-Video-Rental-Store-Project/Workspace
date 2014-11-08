package ua.nure.sigma.store.validator.film;

import org.junit.Test;
import ua.nure.sigma.store.validator.Condition;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FilmRentPriceConditionTest {

    @Test
    public void testValidateEmptyRentPrice(){
        final String expected = FilmRentPriceCondition.EMPTY_RENTAL_PRICE;
        final String toValidate = "";

        // Set up target object.
        Condition condition = new FilmRentPriceCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateSmallRentPrice(){
        final String expected = FilmRentPriceCondition.SMALL_RENTAL_PRICE;
        final String toValidate = "-12";

        // Set up target object.
        Condition condition = new FilmRentPriceCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateIncorrectRentPrice(){
        final String expected = FilmRentPriceCondition.INCORRECT_RENTAL_PRICE;
        final String toValidate = "Fail";

        // Set up target object.
        Condition condition = new FilmRentPriceCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateRentPricePass(){
        final String toValidate = "12";

        // Set up target object.
        Condition condition = new FilmRentPriceCondition();

        // Target assertion.
        assertNull(condition.validate(toValidate));
    }
}