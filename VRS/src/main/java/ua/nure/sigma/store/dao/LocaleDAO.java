package ua.nure.sigma.store.dao;

import ua.nure.sigma.store.entity.Locale;

import java.util.List;

/**
 * Created by vlad on 07.11.14.
 */
public interface LocaleDAO {
    int findLocaleIdByName(String locale);
    String findLocaleNameById(int id);
    List<Locale> findAlLocale();
}
