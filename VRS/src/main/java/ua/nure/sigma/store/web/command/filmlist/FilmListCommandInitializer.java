package ua.nure.sigma.store.web.command.filmlist;

/**
 * Created by Сергей on 11.10.14.
 */
public final class FilmListCommandInitializer {

    private static FilmListCommand command = new FilmListCommand();

    static {
        command.addCommandListener(new FilmListPageCommand());
        command.addCommandListener(new FilmListCategoryCommand());
    }

    public static FilmListCommand getCommand(){
        return command;
    }

}
