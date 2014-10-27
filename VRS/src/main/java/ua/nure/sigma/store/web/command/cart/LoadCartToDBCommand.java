package ua.nure.sigma.store.web.command.cart;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.postgresql.PosgreSqlDAO;
import ua.nure.sigma.store.entity.FilmForRent;
import ua.nure.sigma.store.entity.Rent;
import ua.nure.sigma.store.logic.Cart;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

/**
 * Created by Maksim Sinkevich on 24.10.2014.
 */
public class LoadCartToDBCommand extends Command {

    private static final long MILLISECONDS_IN_DAY = 86400000;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        Cart cart = (Cart) session.getAttribute(Cart.CART_ATTRIBUTE_NAME);
        if(cart.getCurrentCustomer() == null){
            return Paths.COMMAND_CART_DETAILS;
        }
        if(cart.getContent().size() == 0){
            session.removeAttribute(SearchCartCommand.CUSTOMER_FULL_NAME_PARAM_NAME);
            session.removeAttribute(UseBonusCommand.BONUS_IN_USE_PARAM_NAME);
            return Paths.COMMAND_FULL_FILM_LIST;
        }
        Rent rent = new Rent();
        rent.setCustomerID(cart.getCurrentCustomer().getCustomerID());
        List<FilmForRent> list = new ArrayList<FilmForRent>();
        Collection<FilmForRent> set = cart.getContent().values();
        for (FilmForRent filmForRent : set) {
            filmForRent.setAcceptedDate(new Date());
            filmForRent.setFutureDate(new Date(new Date().getTime() + filmForRent.getDays() * MILLISECONDS_IN_DAY));
            list.add(filmForRent);
        }
        rent.setFilmList(list);
        DAOFactory.getInstance().getRentDAO().createRent(rent);

        cart.clear();
        session.removeAttribute(SearchCartCommand.CUSTOMER_FULL_NAME_PARAM_NAME);
        session.removeAttribute(UseBonusCommand.BONUS_IN_USE_PARAM_NAME);
        return Paths.COMMAND_FULL_FILM_LIST;
    }
}
