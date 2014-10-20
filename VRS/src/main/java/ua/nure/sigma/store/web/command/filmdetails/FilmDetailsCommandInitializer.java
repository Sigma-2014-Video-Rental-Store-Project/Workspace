package ua.nure.sigma.store.web.command.filmdetails;

/**
 * Created by Vlad Samotskiy on 18.10.2014.
 */
public class FilmDetailsCommandInitializer {
    private static FilmDetailsCommand command = new FilmDetailsCommand();

    static {
        command.addCommandListener(new FilmDetailsFillCommand());
    }

    public static FilmDetailsCommand getCommand() {
        return command;
    }
}
