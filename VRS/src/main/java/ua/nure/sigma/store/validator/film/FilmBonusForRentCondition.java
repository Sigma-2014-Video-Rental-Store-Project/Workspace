package ua.nure.sigma.store.validator.film;

import ua.nure.sigma.store.validator.Condition;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Validates bonus for rent of film attribute value.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class FilmBonusForRentCondition implements Condition {

    public static final int BONUS_FOR_RENT_LIMIT = 0;
    public static final String EMPTY_BONUS = "The bonus for rent of the film must not be empty.";
    public static final String SMALL_BONUS = "The bonus for rent of the film must not be lower or equals to "
            + BONUS_FOR_RENT_LIMIT + ".";
    public static final String INCORRECT_BONUS = "The bonus for rent of the film must be correct double value.";

    @Override
    public String validate(String attribute) {
        if (attribute.isEmpty()) {
            return EMPTY_BONUS;
        }
        try {
            Locale fmtLocale = Locale.getDefault();
            NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
            long bonusForRent = (long) formatter.parse(attribute).doubleValue() * 100;
            if (bonusForRent < BONUS_FOR_RENT_LIMIT) {
                return SMALL_BONUS;
            }
        } catch (ParseException e) {
            return INCORRECT_BONUS;
        }

        return null;
    }
}
