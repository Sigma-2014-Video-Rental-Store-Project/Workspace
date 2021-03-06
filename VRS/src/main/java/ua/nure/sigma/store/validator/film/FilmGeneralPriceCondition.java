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

	public static final long GENERAL_PRICE_LIMIT = 0;
    public static final String EMPTY_GENERAL_PRICE = "The general price of the film must not be empty.";
    public static final String SMALL_GENERAL_PRICE = "The general price of the film must not be lower or equals to "
            + GENERAL_PRICE_LIMIT + ".";
    public static final String INCORRECT_GENERAL_PRICE = "The general price of the film must be correct integer value.";;

    @Override
	public String validate(String attribute) {
		if (attribute.isEmpty()) {
			return EMPTY_GENERAL_PRICE;
		}
		try {
			Locale fmtLocale = Locale.getDefault();
			NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
			long generalPrice = (long) formatter.parse(attribute).doubleValue() * 100;
			if (generalPrice < GENERAL_PRICE_LIMIT) {
				return SMALL_GENERAL_PRICE;
			}
		} catch (ParseException e) {
			return INCORRECT_GENERAL_PRICE;
		}

		return null;
	}
}
