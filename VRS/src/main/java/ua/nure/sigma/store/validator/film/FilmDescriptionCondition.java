package ua.nure.sigma.store.validator.film;

import ua.nure.sigma.store.validator.Condition;

/**
 * Validates description of film attribute value.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class FilmDescriptionCondition implements Condition {

    public static final int MAX_DESCRIPTION_LENGTH = 360;
    public static final String EMPTY_DESCRIPTION = "The description of the film must not be empty.";
    public static final String TOO_BIG_DESCRIPTION = "The description of the film must not be greater than " + MAX_DESCRIPTION_LENGTH + " symbols.";

    @Override
    public String validate(String attribute) {
        if (attribute.isEmpty()) {
            return EMPTY_DESCRIPTION;
        }
        if (attribute.length() > MAX_DESCRIPTION_LENGTH) {
            return TOO_BIG_DESCRIPTION;
        }

        return null;
    }

}
