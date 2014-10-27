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

	private static final int BONUS_FOR_RENT_LIMIT = 0;

	@Override
	public String validate(String attribute) {
		if (attribute.isEmpty()) {
			return "The bonus for rent of the film must not be empty.";
		}
		try {
			System.out.println(attribute);
			Locale fmtLocale = Locale.getDefault();
			NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
			long bonusForRent = (long) formatter.parse(attribute).doubleValue() * 100;
			if (bonusForRent < BONUS_FOR_RENT_LIMIT) {
				return "The bonus for rent of the film must not be lower or equals to "
						+ BONUS_FOR_RENT_LIMIT + ".";
			}
		} catch (NumberFormatException ex) {
			return "The bonus for rent of the film must be correct integer value.";
		} catch (ParseException e) {
			return "Incorrect locale";
		}

		return null;
	}
}
