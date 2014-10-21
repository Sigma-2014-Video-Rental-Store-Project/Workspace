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

    private static final int RENT_PRICE_LIMIT = 0;

    @Override
    public String validate(String attribute) {
        if (attribute.isEmpty()) {
            return "The rent price of the film must not be empty.";
        }
        try {
//            int rentPrice = (int) Double.parseDouble(attribute) * 100;
            Locale fmtLocale = Locale.getDefault(Locale.Category.FORMAT);
            NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
            int rentPrice =  (int)formatter.parse(attribute).doubleValue()*100;
            if (rentPrice < RENT_PRICE_LIMIT) {
                return "The rent price of the film must not be lower or equals to " + RENT_PRICE_LIMIT + ".";
            }
        } catch (NumberFormatException ex) {
            return "The rent price of the film must be correct integer value.";
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return null;
    }

}
