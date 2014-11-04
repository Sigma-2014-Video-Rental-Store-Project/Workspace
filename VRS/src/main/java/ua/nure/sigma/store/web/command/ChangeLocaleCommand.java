package ua.nure.sigma.store.web.command;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.entity.Admin;
import ua.nure.sigma.store.web.Controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Command for changing current locale.
 * <p/>
 * Created by Maksim Sinkevich on 04.11.2014.
 */
public class ChangeLocaleCommand extends Command {
    private static final Logger LOG = Logger.getLogger(ChangeLocaleCommand.class);


    /**
     * Gets specified locale from parameters and sets it to the configuration.
     */
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        LOG.trace("Change locale command starts.");
        HttpSession session = request.getSession();
        Admin admin = (Admin) session.getAttribute(Controller.USER_ATTRIBUTE_NAME);
        String newLocale = request.getParameter("newLocale");
        return null;
    }
}
