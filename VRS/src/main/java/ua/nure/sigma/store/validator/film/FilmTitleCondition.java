package ua.nure.sigma.store.validator.film;

import ua.nure.sigma.store.validator.Condition;

/**
 * Validates title of film attribute value.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class FilmTitleCondition implements Condition {

    public static final int MAX_TITLE_LENGTH = 40;
    public static final String EMPTY_TITLE = "The title of the film must not be empty.";
    public static final String TOO_BIG_TITLE= "The length of the film title must not be greater than " + MAX_TITLE_LENGTH + " symbols.";

    @Override
    public String validate(String attribute) {
        if (attribute.isEmpty()) {
            return EMPTY_TITLE;
        }
        if (attribute.length() > MAX_TITLE_LENGTH) {
            return TOO_BIG_TITLE;
        }

        return null;
    }
}
