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
    };

    @Override
    protected Object[][] getContents() {
        return new Object[0][];
    }
}
