package ua.nure.sigma.store.web.command.cart;

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
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute(Cart.CART_ATTRIBUTE_NAME);
        String filmIdString = request.getParameter("filmId");
        int filmId = Integer.parseInt(filmIdString);
        cart.remove(filmId);

        return Paths.COMMAND_CART_DETAILS;
    }
}
