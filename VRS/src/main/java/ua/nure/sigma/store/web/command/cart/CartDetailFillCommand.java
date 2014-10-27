package ua.nure.sigma.store.web.command.cart;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Customer;
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
    static final long millisecondsInDay = 86400000;

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        Customers customers = (Customers) request.getSession().getAttribute(CustomerListCommand.CUSTOMERS_PARAM_NAME);
        /*
        * Create customer list if not exitst
        */
        if (customers == null) {
            new CustomerListFillAllCustomersCommand().execute(request, response);
        }
        return Paths.PAGE_CART_DETAIL_FILMS;
    }
}
