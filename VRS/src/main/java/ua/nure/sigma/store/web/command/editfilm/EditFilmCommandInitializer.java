package ua.nure.sigma.store.web.command.editfilm;

/**
 * Created by Maksin Sinkevich on 13.10.2014.
 */
public class EditFilmCommandInitializer {
    private static EditFilmCommand command = new EditFilmCommand();

    static {
        command.addCommandListener(new EditFilmRemoveCommand());
        command.addCommandListener(new EditFilmSaveCommand());
    }

    public static EditFilmCommand getCommand() {
        return command;
    }
}
