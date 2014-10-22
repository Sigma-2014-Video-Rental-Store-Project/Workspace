package ua.nure.sigma.store.validator.film;

import ua.nure.sigma.store.validator.Condition;

/**
 * Validates rental price of film attribute value.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class FilmRentPriceCondition implements Condition {

    private static final int RENT_PRICE_LIMIT = 0;

    @Override
    public String validate(String attribute) {
        if (attribute.isEmpty()) {
            return "The rent price of the film must not be empty.";
        }
        try {
            int rentPrice = (int) Double.parseDouble(attribute) * 100;
            if (rentPrice < RENT_PRICE_LIMIT) {
                return "The rent price of the film must not be lower or equals to " + RENT_PRICE_LIMIT + ".";
            }
        } catch (NumberFormatException ex) {
            return "The rent price of the film must be correct integer value.";
        }

        return null;
    }

}
