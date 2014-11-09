package ua.nure.sigma.store.validator.film;

import org.junit.Test;
import ua.nure.sigma.store.validator.Condition;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class FilmBonusForRentConditionTest {

    @Test
    public void testValidateEmptyBonus(){
        final String expected = FilmBonusForRentCondition.EMPTY_BONUS;
        final String toValidate = "";

        // Set up target object.
        Condition condition = new FilmBonusForRentCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateSmallBonusForRent(){
        final String expected = FilmBonusForRentCondition.SMALL_BONUS;
        final String toValidate = "-12";

        // Set up target object.
        Condition condition = new FilmBonusForRentCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateIncorrectBonusForRent(){
        final String expected = FilmBonusForRentCondition.INCORRECT_BONUS;
        final String toValidate = "Fail";

        // Set up target object.
        Condition condition = new FilmBonusForRentCondition();

        // Target assertion.
        assertEquals(expected, condition.validate(toValidate));
    }

    @Test
    public void testValidateBonusForRentPass(){
        final String toValidate = "12";

        // Set up target object.
        Condition condition = new FilmBonusForRentCondition();

        // Target assertion.
        assertNull(condition.validate(toValidate));
    }
}