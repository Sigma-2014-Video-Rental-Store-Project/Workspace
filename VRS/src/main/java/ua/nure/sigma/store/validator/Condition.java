package ua.nure.sigma.store.validator;

/**
 * This interface provides common contract for all
 * validation conditions.
 *
 * @author Denys Shvchenko
 * @version 1.0
 */
public interface Condition {

    /**
     * Validates the specified attribute. If it is valid, returns
     * {@code null}. If it is not, returns error message that will
     * be displayed to the user.
     *
     * @param attribute that will be validated.
     * @return {@code null} if valid, message if not.
     */
    String validate(String attribute);
}
