package ua.nure.sigma.store.validator.film;

import ua.nure.sigma.store.validator.Condition;

/**
 * Validates title of film attribute value.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class FilmTitleCondition implements Condition {

    private static final int MAX_TITLE_LENGTH = 40;

    @Override
    public String validate(String attribute) {
        if (attribute.isEmpty()) {
            return "The title of the film must not be empty.";
        }
        if (attribute.length() > MAX_TITLE_LENGTH) {
            return "The length of the film title must not be greater than " + MAX_TITLE_LENGTH + " symbols.";
        }

        return null;
    }
}
