package ua.nure.sigma.store.tag;

import ua.nure.sigma.store.logic.Cart;

/**
 * Created by Denis on 10/26/2014.
 */
public class TagFunctions {
    /**
     * If the list of subscribed tariff IDs contains the specified one.
     *  TODO
     *            in what will be searched specified one.
     * @return if 'subscribedTariffIds' contains id from 'tariff' argument.
     */
    public static Boolean isAdded(Integer filmId, Cart cart) {
        return cart.contains(filmId);
    }
}
