package ua.nure.sigma.store.validator.film;

import ua.nure.sigma.store.validator.Condition;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Validates year of film attribute value.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class FilmYearCondition implements Condition {

    public static final int FIRST_FILM_YEAR = 1888;
    public static final String EMPTY_YEAR = "The year of the film must not be empty.";
    public static final String SMALL_YEAR = "The first film appeared in " + FIRST_FILM_YEAR + ".";
    public static final String TOO_BIG_YEAR = "The year of the film must not be greater than current one.";
    public static final String INCORRECT_YEAR = "The year of the film must be correct integer value.";

    @Override
    public String validate(String attribute) {
        if(attribute.isEmpty()){
            return EMPTY_YEAR;
        }
        try {
            int year = Integer.parseInt(attribute);
            if(year < FIRST_FILM_YEAR){
                return SMALL_YEAR;
            }
            if(year > Calendar.getInstance().get(Calendar.YEAR)){
                return TOO_BIG_YEAR;
            }
        } catch (NumberFormatException ex){
            return INCORRECT_YEAR;
        }

        return null;
    }
}
