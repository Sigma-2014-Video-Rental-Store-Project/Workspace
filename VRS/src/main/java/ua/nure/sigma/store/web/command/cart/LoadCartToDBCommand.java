package ua.nure.sigma.store.web.command.cart;

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

        return Paths.PAGE_FILM_LIST;
    }
}
