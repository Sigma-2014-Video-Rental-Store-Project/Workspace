package ua.nure.sigma.store.web.command.editfilm;

/**
 * Created by Maksin Sinkevich on 13.10.2014.
 */
public class EditFilmCommandInitializer {
    private static EditFilmCommand command = new EditFilmCommand();

    static {
    }

    public static EditFilmCommand getCommand() {
        return command;
    }
}
