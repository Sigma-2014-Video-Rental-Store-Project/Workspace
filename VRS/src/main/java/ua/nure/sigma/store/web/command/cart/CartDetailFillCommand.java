package ua.nure.sigma.store.web.command.cart;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.FilmForRent;
import ua.nure.sigma.store.entity.Rent;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.command.customerlist.CustomerListCommand;
import ua.nure.sigma.store.web.command.customerlist.CustomerListFillAllCustomersCommand;
import ua.nure.sigma.store.web.list.Customers;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by Maksim Sinkevich on 20.10.2014.
 */
public class CartDetailFillCommand extends Command {

    public static final String TOTAL_BONUSES_PARAM_NAME = "totalBonuses";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Customers customers = (Customers) request.getSession().getAttribute(CustomerListCommand.CUSTOMERS_PARAM_NAME);
        if (customers == null) {
            new CustomerListFillAllCustomersCommand().execute(request, response);
        }
        Rent rent = (Rent) request.getSession().getAttribute(SearchCartCommand.CART_RENT_PARAM_NAME);
        if (rent != null) {
            List<FilmForRent> filmForRents = rent.getFilmList();
            long totalBonuses = 0;
            int days;
            for (FilmForRent f : filmForRents) {
                days = f.getFutureDate().getDay() - f.getAcceptedDate().getDay();
                totalBonuses += DAOFactory.getInstance().getFilmDAO().findFilmById(f.getFilmID()).getBonusForRent() * days;
            }
            request.setAttribute(TOTAL_BONUSES_PARAM_NAME, totalBonuses);
        }
        return Paths.PAGE_CART_DETAIL_FILMS;
    }
}
