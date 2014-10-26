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
public class ClearCartCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        session.removeAttribute(SearchCartCommand.CUSTOMER_FULL_NAME_PARAM_NAME);
        session.removeAttribute(UseBonusCommand.BONUS_IN_USE_PARAM_NAME);
        Cart cart = (Cart)session.getAttribute(Cart.CART_ATTRIBUTE_NAME);
        cart.clear();

        return Paths.COMMAND_CART_DETAILS;
    }
}
