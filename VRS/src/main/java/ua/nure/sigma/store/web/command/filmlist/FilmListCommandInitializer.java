package ua.nure.sigma.store.web.command.filmlist;

/**
 * Created by Sergey Laposhko on 11.10.14.
 */
public final class FilmListCommandInitializer {

    private static FilmListCommand command = new FilmListCommand();

    static {
        command.addCommandListener(new FilmListCategoryCommand());
        command.addCommandListener(new FilmListAddToCartCommand());
        command.addCommandListener(new FilmListSortCommand());
        command.addCommandListener(new FilmListFilterCommand());
        command.addCommandListener(new FilmListPageCommand());
    }

    /**
     *
     * @return returns complex command with command that want to listen the same request and are connected with this command.
     */
    public static FilmListCommand getCommand() {
        return command;
    }

}
