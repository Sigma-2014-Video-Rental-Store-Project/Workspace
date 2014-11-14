package ua.nure.sigma.store.dao.postgresql;

/**
 * Created by vlad on 14.11.14.
 */
public interface FilmCategoriesSqlQuery {
    static final String SQL_SELECT_FROM_FILM_CATEGORIES_CATEGORIES_BY_FILM_ID =
            "SELECT * FROM FILM_CATEGORIES WHERE film_id = ?";
    static final String SQL_SELECT_FROM_FILM_CATEGORY_BY_CATEGORY_ID = "SELECT FILM_ID FROM FILM_CATEGORIES WHERE CATEGORY_ID = ?";
    static final String SQL_INSERT_INTO_FILM_CATEGORIES = "INSERT INTO FILM_CATEGORIES VALUES(?,?)";
    static final String SQL_DELETE_FILM_CATEGORIES = "DELETE FROM FILM_CATEGORIES WHERE FILM_ID = ?";

}
