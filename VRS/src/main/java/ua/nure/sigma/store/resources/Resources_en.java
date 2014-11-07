package ua.nure.sigma.store.resources;

import java.util.ListResourceBundle;

/**
 * Created by Maksim Sinkevich on 04.11.2014.
 */
public class Resources_en extends ListResourceBundle {
    private Object[][] contents = {
        // Locale block.
        { "locale.jspf.language", "Language" },
        { "locale.jspf.current", "Current:" },
        { "locale.jspf.changeOn", "Change on:" },
        { "login.title", "Sign in Video Rental Store"},
        { "login.CongratText", "Sign in, please."},
        { "login.EmailPlaceholder", "Email address"},
        { "login.PassPlaceholder", "Password"},
        { "login.remember", "Remember me"},
        { "login.btn", "Sign in"},
        { "login.error", "Wrong login or password"},
        { "filmlist.title", "Films"},
        { "filmlist.allCat", ""},
        { "filmlist.all", "All"},
        { "filmlist.avail", "Available"},
        { "filmlist.addNewFilmbtn", "Add new film"},
        { "filmlist.searchPlaceholder", "Keyword"},
        { "filmlist.searchbtn", "Search"},
        { "filmlist.colName", "Name"},
        { "filmlist.colCopies", "Copies left"},
        { "filmlist.colPrice", "Price per day"},
        { "filmlist.colAdd", "Add to cart"},
        { "filmlist.colEdit", "Edit"},
        { "filmlist.editLink", "edit"},
        { "filmlist.addLink", "add"},
        { "filmlist.absent", "absent"},
        { "filmlist.added", "added"},
        { "addfilm.name", "Film name:"},
        { "addfilm.genre", "Genre:"},
        { "addfilm.browse", "Browse..."},
        { "addfilm.copies", "Copies: "},
        { "addfilm.rentPrice", "Rent price ($):"},
        { "addfilm.year", "Year:"},
        { "addfilm.genPrice", "General price ($):"},
        { "addfilm.bonus", "Bonus value:"},
        { "addfilm.cancel", "Cancel"},
        { "addfilm.title", "Add new film"},
        { "addfilm.", ""},
        { "addfilm.", ""},
        { "addfilm.", ""},
    };

    @Override
    protected Object[][] getContents() {
        return new Object[0][];
    }
}
