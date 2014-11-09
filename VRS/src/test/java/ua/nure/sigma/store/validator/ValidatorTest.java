package ua.nure.sigma.store.validator;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ValidatorTest {

    @Test
    public void testValidatorPassAllConditions(){
        final String toValidate = "OK";
        final String nameOfCondition = "conditionMock";
        final String expected = null;

        // Mock condition to imitate passed validation.
        Condition conditionMock = mock(Condition.class);
        when(conditionMock.validate(toValidate)).thenReturn(expected);

        // Prepare mapped parameters to validate.
        Map<String, String> params = new HashMap<String, String>(1);
        params.put(nameOfCondition, toValidate);

        // Set up target object.
        Validator validator = new Validator();
        validator.addCondition(nameOfCondition, conditionMock);

        // Target assertion.
        assertNull(validator.validate(params));
    }

    @Test
         public void testValidatorFailCondition(){
        final String toValidate = "OK";
        final String nameOfCondition = "conditionMock";
        final String expected = "Fail";

        // Mock condition to imitate passed validation.
        Condition conditionMock = mock(Condition.class);
        when(conditionMock.validate(toValidate)).thenReturn(expected);

        // Prepare mapped parameters to validate.
        Map<String, String> params = new HashMap<String, String>(1);
        params.put(nameOfCondition, toValidate);

        // Set up target object.
        Validator validator = new Validator();
        validator.addCondition(nameOfCondition, conditionMock);

        // Target assertion.
        assertEquals(expected, validator.validate(params));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testValidatorNullCondition(){
        final String toValidate = "OK";
        final String nameOfCondition = "conditionMock";
        final String wrongNameOfCondition = "-";
        final String expected = null;

        // Mock condition to imitate passed validation.
        Condition conditionMock = mock(Condition.class);
        when(conditionMock.validate(toValidate)).thenReturn(expected);

        // Prepare mapped parameters to validate.
        // Notice, that mapped condition specified with mistake in name.
        Map<String, String> params = new HashMap<String, String>(1);
        params.put(wrongNameOfCondition, toValidate);

        // Set up target object.
        Validator validator = new Validator();
        validator.addCondition(nameOfCondition, conditionMock);

        // Target assertion: exception must be thrown.
        validator.validate(params);
    }
}