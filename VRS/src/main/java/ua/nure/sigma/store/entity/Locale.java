package ua.nure.sigma.store.entity;

/**
 * Created by vlad on 08.11.14.
 */
public class Locale {
    private int localeId;
    private String name;

    public int getLocaleId() {
        return localeId;
    }

    public void setLocaleId(int localeId) {
        this.localeId = localeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Locale(int localeId, String name) {
        this.localeId = localeId;
        this.name = name;
    }
}
