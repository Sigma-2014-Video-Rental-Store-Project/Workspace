package ua.nure.sigma.store.web;

import java.util.HashMap;
import java.util.Map;

import ua.nure.sigma.store.web.command.Command;

/**
 * This class acts as a encapsulated container for {@code Command} objects. It
 * provides access to the specified values by {@code String} keys. All values
 * must be added to the container by client code.
 * 
 * @author Denys Shevchenko
 * @version 1.0
 */
public final class CommandKeeper {

	private final Map<String, Command> commands;

	/**
	 * Initializes underlying {@code Map} with effective implementation to
	 * provide O(1) speed of appropriate {@code Command} object search.
	 */
	public CommandKeeper() {
		commands = new HashMap<String, Command>();
	}

	/**
	 * Adds new pair of {@code Command} implementation and {@code String} key
	 * connected with it to this container. If the addition process is complete
	 * without {@code IllegalArgumentException}, then added object may be
	 * accessed on this container call by the specified key.
	 * 
	 * @param commandName
	 *            that represents the key for connected {@code Command}
	 *            implementation.
	 * @param command
	 *            that implements {@code Command} contract and may be accessed
	 *            by some key.
	 * 
	 * @throws IllegalArgumentException
	 *             if the {@code commandName} argument is {@code null} or equals
	 *             to empty {@code String} or if the {@code command} argument
	 *             {@code null}.
	 */
	public final void add(String commandName, Command command) {
		if (commandName == null || commandName.isEmpty()) {
			throw new IllegalArgumentException(
					"The name of command must not ne 'null' or empty String.");
		}
		if (command == null) {
			throw new IllegalArgumentException(
					"Command object must not be 'null'");
		}

		commands.put(commandName, command);
	}

	/**
	 * Returns {@code Command} implementation connected with the {@code String}
	 * key specified as an argument in this call. If cannot find appropriate
	 * implementation, returns default {@literal wrongCommand} handler.
	 * 
	 * @param commandName
	 *            is key to the appropriate {@code Command} implementation.
	 * @return {@code Command} implementation connected with specified key.
	 */
	public final Command get(String commandName) {
		if (!commands.containsKey(commandName)) {
			return commands.get("wrong");
		}

		return commands.get(commandName);
	}

}