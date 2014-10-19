package ua.nure.sigma.store.validator;

import java.util.HashMap;
import java.util.Map;

/**
 * Aggregates conditions and {@code String} keys to them. Then validates
 * the list of arguments with keys that connects this data to preset
 * conditions.
 *
 * @author Denys Shvchenko
 * @version 1.0
 */
public class Validator {

    private Map<String, Condition> conditions;

    /**
     * Initializes data container for keys and conditions.
     */
    public Validator() {
        conditions = new HashMap<String, Condition>(10);
    }

    /**
     * Adds new {@code Condition} that may be accessed by specified key.
     *
     * @param key       that represents attribute name.
     * @param condition that will validate specified attribute.
     */
    public void addCondition(String key, Condition condition) {
        conditions.put(key, condition);
    }

    /**
     * Validates all specified attributes by their conditions, which
     * are found by {@code String} keys.
     *
     * @param attributes that will be validated.
     * @return error message if validation fails or {@code null}
     * if all attributes are valid.
     */
    public String validate(Map<String, String> attributes) {
        for (Map.Entry<String, String> entry : attributes.entrySet()) {
            Condition condition = conditions.get(entry.getKey());
            if (condition == null) {
                throw new IllegalArgumentException("Cannot validate value of the '" + entry.getKey() + "' attribute.");
            }
            String errorMessage = condition.validate(entry.getValue());
            if (errorMessage != null) {
                return errorMessage;
            }
        }

        return null;
    }
}
