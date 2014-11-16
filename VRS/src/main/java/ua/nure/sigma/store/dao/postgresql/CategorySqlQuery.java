package ua.nure.sigma.store.dao.postgresql;

/**
 * Created by vlad on 16.11.14.
 */
public interface CategorySqlQuery {
    static final String SQL_SELECT_FROM_CATEGORIES_BY_ID_LOCALE = "SELECT * FROM CATEGORY_LOCALE WHERE CATEGORY_ID = ? and locale_id=?";
    static final String SQL_SELECT_FROM_CATEGORIES= "SELECT * FROM CATEGORY_LOCALE WHERE LOCALE_ID = 1";
    static final String SQL_SELECT_FROM_CATEGORIES_LOCALE = "SELECT * FROM CATEGORY_LOCALE WHERE LOCALE_ID = ?";
    static final String SQL_SELECT_FROM_CATEGORIES_BY_ID = "SELECT * FROM CATEGORY_LOCALE WHERE CATEGORY_ID = ? AND LOCALE_ID = 1";
    static final String SQL_SELECT_FROM_CATEGORIES_BY_NAME =
            "SELECT CATEGORY_ID FROM CATEGORY_LOCALE WHERE NAME = ?";
    static final String CATEGORY_ID_PARAM = "category_id";
    static final String CATEGORY_NAME_PARAM = "name";
}
