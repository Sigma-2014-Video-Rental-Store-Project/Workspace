package ua.nure.sigma.store.logic;

/**
 * This class implements "Factory method" design pattern for
 * {@code Cart} object instantiation.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public final class CartCreator {

    /**
     * Prevents instantiation of the service class.
     */
    private CartCreator() {
        throw new AssertionError("Must not be instantiated.");
    }

    /**
     * Instantiates new {@code Cart} object.
     *
     * @return new instance of the cart.
     */
    public static Cart newCart() {
        return new Cart();
    }
}
