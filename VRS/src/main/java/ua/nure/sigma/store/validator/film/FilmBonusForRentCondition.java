package ua.nure.sigma.store.validator.film;

import ua.nure.sigma.store.validator.Condition;

/**
 * Validates bonus for rent of film attribute value.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class FilmBonusForRentCondition implements Condition {

    private static final int BONUS_FOR_RENT_LIMIT = 0;

    @Override
    public String validate(String attribute) {
        if (attribute.isEmpty()) {
            return "The bonus for rent of the film must not be empty.";
        }
        try {
            int bonusForRent = Integer.parseInt(attribute);
            if (bonusForRent < BONUS_FOR_RENT_LIMIT) {
                return "The bonus for rent of the film must not be lower or equals to " + BONUS_FOR_RENT_LIMIT + ".";
            }
        } catch (NumberFormatException ex) {
            return "The bonus for rent of the film must be correct integer value.";
        }

        return null;
    }
}
