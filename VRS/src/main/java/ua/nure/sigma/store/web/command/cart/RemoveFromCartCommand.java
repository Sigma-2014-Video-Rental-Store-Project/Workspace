package ua.nure.sigma.store.web.command.cart;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.logic.Cart;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by Denis on 10/26/2014.
 */
public class RemoveFromCartCommand extends Command {

    private static final Logger LOG = Logger.getLogger(RemoveFromCartCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Started to process remove from cart.");
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute(Cart.CART_ATTRIBUTE_NAME);
        String filmIdString = request.getParameter("filmId");
        LOG.debug("Film ID to remove: "+filmIdString);
        int filmId = Integer.parseInt(filmIdString);
        cart.remove(filmId);

        return Paths.COMMAND_CART_DETAILS;
    }
}
