package ua.nure.sigma.store.dao.postgresql;

/**
 * Created by vlad on 16.11.14.
 */
public interface LocaleSqlQuery {

    static final String SQL_SELECT_FROM_LOCALE_BY_ID = "SELECT * FROM LOCALE WHERE LOCALE_ID = ?";
    static final String SQL_SELECT_FROM_LOCALE_BY_NAME = "SELECT * FROM LOCALE WHERE NAME = ?";
    static final String SQL_SELECT_FROM_LOCALE = "SELECT * FROM LOCALE";

    static final String LOCALE_ID_PARAM = "locale_id";
    static final String LOCALE_NAME_PARAM = "name";

}
