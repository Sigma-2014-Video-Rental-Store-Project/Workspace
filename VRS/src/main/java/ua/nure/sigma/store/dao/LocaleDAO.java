package ua.nure.sigma.store.dao;

/**
 * Created by vlad on 07.11.14.
 */
public interface LocaleDAO {
    int findLocaleIdByName(String locale);
    String findLocaleNameById(int id);
}
