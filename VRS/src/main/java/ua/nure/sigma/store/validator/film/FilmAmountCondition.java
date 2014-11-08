package ua.nure.sigma.store.validator.film;

import ua.nure.sigma.store.validator.Condition;

/**
 * Validates amount of film attribute value.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class FilmAmountCondition implements Condition {

    public static final int AMOUNT_LIMIT = 0;
    public static final String EMPTY_AMOUNT = "The amount of the film must not be empty.";
    public static final String SMALL_AMOUNT = "The amount of the film must not be lower or equals to " + AMOUNT_LIMIT + ".";
    public static final String INCORRECT_AMOUNT = "The amount of the film must be correct integer value.";

    @Override
    public String validate(String attribute) {
        if (attribute.isEmpty()) {
            return EMPTY_AMOUNT;
        }
        try {
            int amount = Integer.parseInt(attribute);
            if (amount < AMOUNT_LIMIT) {
                return SMALL_AMOUNT;
            }
        } catch (NumberFormatException ex) {
            return INCORRECT_AMOUNT;
        }

        return null;
    }
}
