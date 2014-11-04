package ua.nure.sigma.store.resources;

import java.util.ListResourceBundle;

/**
 * Created by Maksim Sinkevich on 04.11.2014.
 */
public class Resources_ru extends ListResourceBundle {
    private Object[][] contents = {
        // Locale block.
        { "locale.jspf.language", "Язык" },
        { "locale.jspf.current", "Текущий:" },
        { "locale.jspf.changeOn", "Замена:" }
    };

    @Override
    protected Object[][] getContents() {
        return new Object[0][];
    }
}
