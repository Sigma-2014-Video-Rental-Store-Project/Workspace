package ua.nure.sigma.store.dao.postgresql;

/**
 * Created by vlad on 16.11.14.
 */
public interface SexSqlQuery {
    static final String SQL_SELECT_FROM_SEX_BY_ID = "SELECT * FROM SEX_LOCALE WHERE SEX_ID = ? AND LOCALE_ID = ?";
    static final String SQL_SELECT_FROM_SEX_ALL_SEX = "SELECT * FROM SEX_LOCALE WHERE LOCALE_ID =?";
    static final String SQL_SELECT_FROM_SEX_BY_NAME =
            "SELECT * FROM SEX_LOCALE WHERE name = ?";
    static final String SEX_ID_PARAM = "SEX_ID";
    static final String SEX_NAME_PARAM = "name";
}
