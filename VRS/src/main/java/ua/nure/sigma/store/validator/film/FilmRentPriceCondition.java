package ua.nure.sigma.store.validator.film;

import ua.nure.sigma.store.validator.Condition;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Validates rental price of film attribute value.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class FilmRentPriceCondition implements Condition {

    public static final long RENT_PRICE_LIMIT = 0;
    public static final String EMPTY_RENTAL_PRICE = "The rent price of the film must not be empty.";
    public static final String SMALL_RENTAL_PRICE = "The rent price of the film must not be lower or equals to " + RENT_PRICE_LIMIT + ".";
    public static final String INCORRECT_RENTAL_PRICE = "The rent price of the film must be correct integer value.";

    @Override
    public String validate(String attribute) {
        if (attribute.isEmpty()) {
            return EMPTY_RENTAL_PRICE;
        }
        try {
            Locale fmtLocale = Locale.getDefault();
            NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
            long rentPrice =  (long)formatter.parse(attribute).doubleValue()*100;
            if (rentPrice < RENT_PRICE_LIMIT) {
                return SMALL_RENTAL_PRICE;
            }
        } catch (ParseException e) {
            return INCORRECT_RENTAL_PRICE;
        }

        return null;
    }

}
