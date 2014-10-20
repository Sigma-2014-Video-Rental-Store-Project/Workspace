package ua.nure.sigma.store.validator.film;

import ua.nure.sigma.store.validator.Condition;

/**
 * Validates description of film attribute value.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class FilmDescriptionCondition implements Condition {

    private static final int MAX_DESCRIPTION_LENGTH = 160;

    @Override
    public String validate(String attribute) {
        if (attribute.isEmpty()) {
            return "The description of the film must not be empty.";
        }
        if (attribute.length() > MAX_DESCRIPTION_LENGTH) {
            return "The description of the film must not be greater than " + MAX_DESCRIPTION_LENGTH + " symbols.";
        }

        return null;
    }

}
