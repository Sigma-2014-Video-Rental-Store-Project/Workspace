package ua.nure.sigma.store.web;


import ua.nure.sigma.store.validator.Validator;
import ua.nure.sigma.store.validator.film.*;
import ua.nure.sigma.store.web.command.ChangeLocaleCommand;
import ua.nure.sigma.store.web.command.LogOutCommand;
import ua.nure.sigma.store.web.command.SignInCommand;
import ua.nure.sigma.store.web.command.WrongCommand;
import ua.nure.sigma.store.web.command.addNewAdmin.AddNewAdminCommand;
import ua.nure.sigma.store.web.command.addNewAdmin.CreateNewAdminCommand;
import ua.nure.sigma.store.web.command.addNewCustomer.AddNewCustomerCommand;
import ua.nure.sigma.store.web.command.addNewCustomer.CreateCustomerCommand;
import ua.nure.sigma.store.web.command.addNewFilm.AddNewFilmCommand;
import ua.nure.sigma.store.web.command.addNewFilm.AddNewFilmSaveCommand;
import ua.nure.sigma.store.web.command.adminList.AdminListCommand;
import ua.nure.sigma.store.web.command.adminList.ChangeAdminPasswordCommand;
import ua.nure.sigma.store.web.command.adminList.DeleteAdminCommand;
import ua.nure.sigma.store.web.command.cart.*;
import ua.nure.sigma.store.web.command.customerDetails.CustomerDetailsCommand;
import ua.nure.sigma.store.web.command.customerlist.CustomerListCommandInitializer;
import ua.nure.sigma.store.web.command.editCustomer.DeleteCustomerCommand;
import ua.nure.sigma.store.web.command.editCustomer.EditCustomerCommand;
import ua.nure.sigma.store.web.command.editCustomer.UpdateCustomerCommand;
import ua.nure.sigma.store.web.command.editfilm.EditFilmCommand;
import ua.nure.sigma.store.web.command.editfilm.EditFilmRemoveCommand;
import ua.nure.sigma.store.web.command.editfilm.EditFilmSaveCommand;
import ua.nure.sigma.store.web.command.filmdetails.FilmDetailsCommandInitializer;
import ua.nure.sigma.store.web.command.filmlist.FilmListCommandInitializer;
import ua.nure.sigma.store.web.command.customerDetails.FimReturnCommand;

import java.util.ArrayList;
import java.util.List;

/**
 * This service class provides transactional instantiation of
 * {@code CommandKeeper} and addition a list of the specified {@code Command}
 * objects and {@code String} keys connected with each of them.
 *
 * @author Denys Shevchenko
 * @version 1.0
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
     * @param commandKeeper that will be filled with key-command pairs.
     */
    private static void fillWithCommands(CommandKeeper commandKeeper) {
        commandKeeper.add("wrong", new WrongCommand());
        commandKeeper.add("signIn", new SignInCommand());
        commandKeeper.add("logout", new LogOutCommand());
        commandKeeper.add("fullFilmList", FilmListCommandInitializer.getCommand());
        commandKeeper.add("customerList", CustomerListCommandInitializer.getCommand());
        // Edit form specific commands.
        List<String> extensions = new ArrayList<String>();
        extensions.add(".jpg");
        extensions.add(".jpeg");
        extensions.add(".png");
        extensions.add(".gif");
        extensions.add(".bmp");

        Validator validator = new Validator();
        validator.addCondition("title", new FilmTitleCondition());
        validator.addCondition("amount", new FilmAmountCondition());
        validator.addCondition("description", new FilmDescriptionCondition());
        validator.addCondition("generalPrice", new FilmGeneralPriceCondition());
        validator.addCondition("rentPrice", new FilmRentPriceCondition());
        validator.addCondition("bonusForRent", new FilmBonusForRentCondition());
        validator.addCondition("year", new FilmYearCondition());

        commandKeeper.add("editFilm", new EditFilmCommand());
        commandKeeper.add("editFilmRemove", new EditFilmRemoveCommand());
        commandKeeper.add("editFilmSave", new EditFilmSaveCommand(extensions, validator));
        commandKeeper.add("filmDetails", FilmDetailsCommandInitializer.getCommand());

        commandKeeper.add("cartDetails", new CartDetailFillCommand());
        commandKeeper.add("cartSearch", new SearchCartCommand());
        commandKeeper.add("cartContinue", new LoadCartToDBCommand());
        commandKeeper.add("cartAdd", new AddToCartCommand());
        commandKeeper.add("cartClear", new ClearCartCommand());
        commandKeeper.add("cartRemove", new RemoveFromCartCommand());
        commandKeeper.add("cartCancelCustomer", new CancelSelectedCustomer());
        commandKeeper.add("cartUseBonus", new UseBonusCommand());
        commandKeeper.add("cartUpdate", new UpdateCartCommand());
       commandKeeper.add("viewOrderDetails", new ViewOrderDetailsCommand());

        commandKeeper.add("addNewFilm", new AddNewFilmCommand());
        commandKeeper.add("addNewFilmSave", new AddNewFilmSaveCommand(extensions, validator));

        //add command to edit customer
        commandKeeper.add("editCustomer", new EditCustomerCommand());
        commandKeeper.add("editCustomerSave", new UpdateCustomerCommand(extensions));
        commandKeeper.add("editCustomerRemove", new DeleteCustomerCommand());

        //add command to create customer
        commandKeeper.add("addNewCustomer", new AddNewCustomerCommand());
        commandKeeper.add("addNewCustomerSave", new CreateCustomerCommand(extensions));

        commandKeeper.add("customerDetails", new CustomerDetailsCommand());
        commandKeeper.add("returnFilm", new FimReturnCommand());

        commandKeeper.add("adminList", new AdminListCommand());
        commandKeeper.add("deleteAdmin", new DeleteAdminCommand());
        commandKeeper.add("changeAdminPassword", new ChangeAdminPasswordCommand());

        commandKeeper.add("changeLocale", new ChangeLocaleCommand());

        commandKeeper.add("addNewAdmin", new AddNewAdminCommand());
        commandKeeper.add("createNewAdmin", new CreateNewAdminCommand());
    }

}