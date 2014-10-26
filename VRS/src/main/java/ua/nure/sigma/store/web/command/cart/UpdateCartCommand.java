package ua.nure.sigma.store.web.command.cart;

import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.entity.FilmForRent;
import ua.nure.sigma.store.logic.Cart;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;

/**
 * Created by Denis on 10/26/2014.
 */
public class UpdateCartCommand extends Command {

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Cart cart = (Cart)session.getAttribute(Cart.CART_ATTRIBUTE_NAME);
        for(Map.Entry<Film, FilmForRent> entry: cart.getContent().entrySet()){
            String copiesString = request.getParameter("copies"+entry.getKey().getFilmId());
            int copies = Integer.parseInt(copiesString);
            String daysString = request.getParameter("days"+entry.getKey().getFilmId());
            int days = Integer.parseInt(daysString);
            entry.getValue().setCopies(copies);
            entry.getValue().setDays(days);
        }
        return Paths.COMMAND_CART_DETAILS;
    }
}
