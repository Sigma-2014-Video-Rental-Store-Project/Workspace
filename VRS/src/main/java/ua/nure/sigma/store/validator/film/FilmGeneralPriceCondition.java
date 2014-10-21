package ua.nure.sigma.store.validator.film;

import ua.nure.sigma.store.validator.Condition;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

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
            // to cent
            Locale fmtLocale = Locale.getDefault(Locale.Category.FORMAT);
            NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
            int generalPrice =  (int)formatter.parse(attribute).doubleValue()*100;
          //  int generalPrice =  (int) Double.parseDouble(attribute) * 100;
            if (generalPrice < GENERAL_PRICE_LIMIT) {
                return "The general price of the film must not be lower or equals to " + GENERAL_PRICE_LIMIT + ".";
            }
        } catch (NumberFormatException ex) {
            return "The general price of the film must be correct integer value.";
        } catch (ParseException e) {
            return "Incorrect locale";
        }
        return null;
    }
}
