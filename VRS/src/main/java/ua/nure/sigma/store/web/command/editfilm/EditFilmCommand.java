package ua.nure.sigma.store.web.command.editfilm;

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
 * Created by Maksin Sinkevich on 13.10.2014.
 */
public class EditFilmCommand extends Command implements IComplexCommand {

    static final String FILMID_PARAM_NAME = "filmID";
    static final String REMOVE_PARAM_NAME = "remove";

    List<Command> commandsListeners;

    EditFilmCommand() {
        commandsListeners = new ArrayList<Command>();
    }

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        notifyAllCommands(request, response);
        System.err.println(request.getParameter("filmId"));
        return Paths.PAGE_EDIT_FILM;
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
