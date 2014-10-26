package ua.nure.sigma.store.web.command.cart;

import ua.nure.sigma.store.logic.Pager;
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
public class UseBonusCommand extends Command {

    public static final String BONUS_IN_USE_PARAM_NAME = "bonusInUse";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        HttpSession session = request.getSession();
        String bonusInUseString = request.getParameter("bonusToUse");
        Long bonusInUse = Long.parseLong(bonusInUseString);
        session.setAttribute(BONUS_IN_USE_PARAM_NAME, bonusInUse);
        return Paths.COMMAND_CART_DETAILS;
    }
}
