package ua.nure.sigma.store.web.command.addNewCustomer;

import ua.nure.sigma.store.web.command.Command;

/**
 * Created by vlad on 22.10.14.
 */
public class NewCustomerCommandInitializer {

    private static NewCustomerListCommand command;

    static {
        command = new NewCustomerListCommand();
        command.addCommandListener(new AddNewCustomerCommand());
//        command.addCommandListener(new FilmListSearchCommand());
//        command.addCommandListener(new FilmListAddToCartCommand());
//        command.(new CustomerListSortCommand());
//        command.addCommandListener(new CustomerListFilterCommand());
//        command.addCommandListener(new CustomerListPageCommand());
    }

    public static Command getCommand(){
        return null;
    }
}

