package ua.nure.sigma.store.web.command.cart;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmDAO;
import ua.nure.sigma.store.entity.*;
import ua.nure.sigma.store.logic.Cart;
import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by vlad on 04.11.14.
 */
public class ViewOrderDetailsCommand extends Command{
    private static final String ORDER_DETAIL_OBJECT = "rentView";

    private static final Logger LOG = Logger.getLogger(ViewOrderDetailsCommand.class);
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        ///TODO: parse from session parameter
        LOG.debug("ViewOrderDetailsCommand started.");
        return Paths.PAGE_VIEW_ORDER_DETAIL;
    }

}

