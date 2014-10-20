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

    private static final int FIRST_FILM_YEAR = 1888;

    @Override
    public String validate(String attribute) {
        if(attribute.isEmpty()){
            return "The year of the film must not be empty.";
        }
        try {
            int year = Integer.parseInt(attribute);
            if(year < FIRST_FILM_YEAR){
                return "There were no cinema in " + year + " at all.";
            }
            if(year > Calendar.getInstance().get(Calendar.YEAR)){
                return "The year of the film must not be greater than current one.";
            }
        } catch (NumberFormatException ex){
            return "The year of the film must be correct integer value.";
        }

        return null;
    }
}
