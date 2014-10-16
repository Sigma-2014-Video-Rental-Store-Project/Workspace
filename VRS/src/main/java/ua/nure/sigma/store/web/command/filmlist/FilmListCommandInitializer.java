package ua.nure.sigma.store.web.command.filmlist;

/**
 * Created by Sergey Laposhko on 11.10.14.
 */
public final class FilmListCommandInitializer {

    private static FilmListCommand command = new FilmListCommand();

    static {
        command.addCommandListener(new FilmListCategoryCommand());
        command.addCommandListener(new FilmListPageCommand());
        command.addCommandListener(new FilmListSortCommand());
    }

    public static FilmListCommand getCommand() {
        return command;
    }

}
