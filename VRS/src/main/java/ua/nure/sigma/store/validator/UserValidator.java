package ua.nure.sigma.store.validator;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserValidator {
	/**
	 * Methods to validate customer's parameters.
	 * 
	 * For use this validator you need to import class of this validator to your class.
	 * 
	 * @author Vlad Samotskiy
	 * @version 1.0
	 */
	public static String validatePartOfName(String input) {
		if (input.isEmpty()) {
			return "The part of name must not be empty.";
		}
		int maxLen = 20;
		if (input.length() > maxLen) {
			return "The part of name must not be lower or equals to " + maxLen
					+ ".";
		}
		return null;
	}

	public static String validateEmail(String input) {
		if (input.isEmpty()) {
			return "The email must not be empty.";
		}
		String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
		Pattern p = Pattern.compile(ePattern);
		Matcher m = p.matcher(input);
		if (!m.matches()) {
			return "Email is not correct value.";
		}
		return null;
	}

	public static String validatePhone(String input) {
		if (input.isEmpty()) {
			return "The phone must not be empty.";
		}
		if (!input.matches("[0-9]+")) {
			return "Phone must contains only numbers";
		}
		return null;
	}

	public static String validateBonus(String input) {
		int limit = 0;
		if (input.isEmpty()) {
			return "The bonus for rent of the film must not be empty.";
		}
		try {
			System.out.println(input);
			Locale fmtLocale = Locale.getDefault();
			NumberFormat formatter = NumberFormat.getInstance(fmtLocale);
			long bonusForRent = (long) formatter.parse(input).doubleValue() * 100;
			if (bonusForRent < limit) {
				return "The user's bonuses must not be lower or equals to "
						+ limit + ".";
			}
		} catch (NumberFormatException ex) {
			return "The user's bonuses must be correct integer value.";
		} catch (ParseException e) {
			return "Incorrect locale";
		}

		return null;
	}
}
