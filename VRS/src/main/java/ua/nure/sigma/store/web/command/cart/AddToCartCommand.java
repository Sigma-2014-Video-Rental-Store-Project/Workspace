package ua.nure.sigma.store.web.command.cart;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.logic.Cart;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Adds a film to the shopping cart by its ID. If this
 * film has already been added, than ignores requested
 * operation.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class AddToCartCommand extends Command {

    private static final String RETURN_TO_PAGE_PARAM_NAME = "returnTo";

    private static final Logger LOG = Logger.getLogger(AddToCartCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.debug("Add to cart command started.");
        String filmIdString = request.getParameter("filmId");
        int filmId = Integer.parseInt(filmIdString);
        Cart cart = (Cart) request.getSession().getAttribute(Cart.CART_ATTRIBUTE_NAME);
        if (!cart.contains(filmId)) {
            boolean success = cart.addNewFilm(filmId);
            if(!success){
                LOG.info("Cannot add specified film to the cart (already added).");
            }
        }
        String returnTo = request.getParameter(RETURN_TO_PAGE_PARAM_NAME);
        LOG.debug("Add to cart command finished. Redirection on: " + returnTo);

        return returnTo;
    }
}
