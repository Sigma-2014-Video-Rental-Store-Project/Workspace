package ua.nure.sigma.store.web.command.cart;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Rent;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Maksim Sinkevich on 24.10.2014.
 */
public class LoadCartToDBCommand extends Command {
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
//        Rent cartRent = (Rent) request.getSession().getAttribute(SearchCartCommand.CART_RENT_PARAM_NAME);
//        if (cartRent == null || cartRent.getCustomerID() == 0) {
//            return Paths.PAGE_CART_DETAIL_FILMS;
//        }
//        DAOFactory.getInstance().getRentDAO().createRent(cartRent);
//        request.getSession().setAttribute(SearchCartCommand.CART_RENT_PARAM_NAME, new Rent());
        return Paths.PAGE_FILM_LIST;
    }
}
