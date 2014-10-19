package ua.nure.sigma.store.validator.film;

import ua.nure.sigma.store.validator.Condition;

/**
 * Validates amount of film attribute value.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class FilmAmountCondition implements Condition {

    private static final int AMOUNT_LIMIT = 0;

    @Override
    public String validate(String attribute) {
        if (attribute.isEmpty()) {
            return "The amount of the film must not be empty.";
        }
        try {
            int bonusForRent = Integer.parseInt(attribute);
            if (bonusForRent < AMOUNT_LIMIT) {
                return "The amount of the film must not be lower or equals to " + AMOUNT_LIMIT + ".";
            }
        } catch (NumberFormatException ex) {
            return "The amount of the film must be correct integer value.";
        }

        return null;
    }
}
