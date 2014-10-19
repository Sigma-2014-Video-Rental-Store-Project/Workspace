package ua.nure.sigma.store.web.command.filmdetails;

import ua.nure.sigma.store.web.Paths;
import ua.nure.sigma.store.web.command.Command;
import ua.nure.sigma.store.web.command.filmlist.IComplexCommand;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Vlad Samotskiy on 18.10.2014.
 */
public class FilmDetailsCommand extends Command implements IComplexCommand {

	static final String FILMID_PARAM_NAME = "filmId";
    static final String FILM_TITLE_PARAM_NAME = "filmTitle";
    static final String FILM_CATEGORIES_PARAM_NAME = "categoryName";
    static final String FILM_AMOUNT_PARAM_NAME = "amount";
    static final String FILM_DESCRIPTION_PARAM_NAME = "description";
    static final String FILM_GENERAL_PRICE_PARAM_NAME = "generalPrice";
    static final String FILM_RENT_PRICE_PARAM_NAME = "rentPrice";
    static final String FILM_BONUS_PARAM_NAME = "bonus";
    static final String FILM_YEAR_PARAM_NAME = "year";
    static final String REQUEST_PARAM_NAME = "get";
    static final String POST_RESULT = "controller?command=filmDetails&get=true";

    List<Command> commandsListeners;

    FilmDetailsCommand() {
        commandsListeners = new ArrayList<Command>();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        notifyAllCommands(request, response);
        String get = request.getParameter(FilmDetailsCommand.REQUEST_PARAM_NAME);
        if (get != null && get.equals("true")) {
            return Paths.PAGE_FILM_DETAILS;
        }
        return POST_RESULT;
    }

    private void notifyAllCommands(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        for (Command command : commandsListeners) {
            command.execute(request, response);
        }
    }

    @Override
    public void addCommandListener(Command command) {
        commandsListeners.add(command);
    }
}