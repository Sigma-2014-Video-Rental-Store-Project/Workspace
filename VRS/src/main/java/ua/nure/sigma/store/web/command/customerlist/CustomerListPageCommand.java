package ua.nure.sigma.store.web.command.customerlist;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.list.Films;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by  on 20.10.14.
 */
public class CustomerListPageCommand extends Command {

    private static final Logger LOG = Logger.getLogger(CustomerListPageCommand.class);

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String pageString = (String) request.getParameter(CustomerListCommand.PAGE_PARAM_NAME);

        if (pageString != null && !pageString.equals("")) {
            //changing page index
            Films filmsParam = (Films) request.getSession().getAttribute(CustomerListCommand.CUSTOMERS_PARAM_NAME);
            filmsParam.setPageIndex(Integer.valueOf(pageString));
            LOG.debug("Selected page = " + pageString);
        }

        return null;
    }
}
