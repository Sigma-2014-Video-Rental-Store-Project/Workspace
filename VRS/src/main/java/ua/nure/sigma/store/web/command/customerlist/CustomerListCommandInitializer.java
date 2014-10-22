package ua.nure.sigma.store.web.command.customerlist;

import ua.nure.sigma.store.web.command.Command;

/**
 * Created by Sergey Laposhko on 20.10.14.
 */
public class CustomerListCommandInitializer {
    private static CustomerListCommand command;

    static {
        command = new CustomerListCommand();
        command.addCommandListener(new CustomerListFillAllCustomersCommand());
//        command.addCommandListener(new FilmListSearchCommand());
//        command.addCommandListener(new FilmListAddToCartCommand());
        command.addCommandListener(new CustomerListSortCommand());
        command.addCommandListener(new CustomerListFilterCommand());
        command.addCommandListener(new CustomerListPageCommand());
    }

    public static Command getCommand(){
        return command;
    }
}
