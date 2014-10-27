package ua.nure.sigma.store.web.list;

import ua.nure.sigma.store.entity.Sex;

import java.util.List;

/**
 * Created by Maxim on 10.10.2014.
 */
public class Sexes {
    private List<Sex> sexes;

    public Sexes(List<Sex> sexes) {
        this.sexes = sexes;
    }

    public List<Sex> getModel() {
        return sexes;
    }
}
