package ua.nure.sigma.store.validator.film;

import ua.nure.sigma.store.validator.Condition;

/**
 * Validates general price of film attribute value.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class FilmGeneralPriceCondition implements Condition {

    private static final int GENERAL_PRICE_LIMIT = 0;

    @Override
    public String validate(String attribute) {
        if (attribute.isEmpty()) {
            return "The general price of the film must not be empty.";
        }
        try {
            int generalPrice = Integer.parseInt(attribute);
            if (generalPrice < GENERAL_PRICE_LIMIT) {
                return "The general price of the film must not be lower or equals to " + GENERAL_PRICE_LIMIT + ".";
            }
        } catch (NumberFormatException ex) {
            return "The general price of the film must be correct integer value.";
        }

        return null;
    }
}
