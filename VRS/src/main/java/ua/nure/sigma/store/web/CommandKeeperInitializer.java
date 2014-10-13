package ua.nure.sigma.store.web;

import ua.nure.sigma.store.web.command.*;
import ua.nure.sigma.store.web.command.SignInCommand;
import ua.nure.sigma.store.web.command.WrongCommand;
import ua.nure.sigma.store.web.command.filmlist.FilmListCommandInitializer;

/**
 * This service class provides transactional instantiation of
 * {@code CommandKeeper} and addition a list of the specified {@code Command}
 * objects and {@code String} keys connected with each of them.
 *
 * @author Denys Shevchenko
 *
 * @version 1.0
 *
 * @see CommandKeeper
 */
public final class CommandKeeperInitializer {

    /**
     * Protects service class from mistaken instantiation.
     */
    private CommandKeeperInitializer() {

        // Must not be called.
        throw new AssertionError("Must not be instantiated.");
    }

    /**
     * Creates new instance of the {@code CommandKeeper} and fills it with a
     * list of the specified {@code Command} objects and {@code String} keys
     * connected with each of them.
     *
     * @return new instance of filled command keeper.
     */
    public static CommandKeeper newCommandKeeper() {
        CommandKeeper commandKeeper = new CommandKeeper();
        fillWithCommands(commandKeeper);

        return commandKeeper;
    }

    /**
     * Fills {@code CommandKeeper} with a list of the specified {@code Command}
     * objects and {@code String} keys connected with each of them.
     *
     * @param commandKeeper
     *            that will be filled with key-command pairs.
     */
    private static void fillWithCommands(CommandKeeper commandKeeper) {
        commandKeeper.add("wrong", new WrongCommand());
        commandKeeper.add("signIn", new SignInCommand());
        commandKeeper.add("fullFilmList", FilmListCommandInitializer.getCommand());
        commandKeeper.add("logout", new LogOutCommand());
    }

}