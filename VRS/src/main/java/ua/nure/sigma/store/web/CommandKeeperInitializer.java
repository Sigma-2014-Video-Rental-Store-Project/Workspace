package ua.nure.sigma.store.web;

import ua.nure.sigma.store.web.command.WrongCommand;

public final class CommandKeeperInitializer {

	public static CommandKeeper newCommandKeeper() {
		CommandKeeper commandKeeper = new CommandKeeper();
		commandKeeper.add("wrongCommand", new WrongCommand());

		return commandKeeper;
	}

}
