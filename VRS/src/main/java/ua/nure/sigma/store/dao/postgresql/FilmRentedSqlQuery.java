package ua.nure.sigma.store.dao.postgresql;

/**
 * Created by vlad on 16.11.14.
 */
public interface FilmRentedSqlQuery {
    static final String SQL_SELECT_FROM_FILM_AT_RENT_BY_COPIES_AT_RENT =
            "SELECT sum(count) as rentedCopies FROM FILM_AT_RENT WHERE FILM_ID = ? AND ACCEPTED_DATE IS NULL";
    static final String SQL_SELECT_FROM_FILM_RENTED_BY_RENT_ID = "SELECT * FROM FILM_AT_RENT WHERE RENT_ID = ?";
    static final String SQL_SELECT_FILM_RENTED = "SELECT * FROM FILM_AT_RENT WHERE RENT_ID = ? AND FILM_ID = ?";
    static final String SQL_INSERT_INTO_FILM_AT_RENTED = "INSERT INTO FILM_AT_RENT VALUES(?,?,?,?,NULL,?)";
    static final String SQL_UPDATE_FILM_RENTED = "UPDATE FILM_AT_RENT SET COPIES_LEFT = ? WHERE RENT_ID =? AND FILM_ID = ?";
    static final String SQL_CLOSE_FILM_RENT = "UPDATE FILM_AT_RENT SET ACCEPTED_DATE = CURRENT_DATE, COPIES_LEFT = 0 WHERE RENT_ID = ? AND FILM_ID = ?";

    static final String FILM_ID_PARAM ="FILM_ID";
    static final String COUNT_PARAM ="COUNT";
    static final String COPIES_LEFT_PARAM ="COPIES_LEFT";
    static final String ACCEPTED_DATE_PARAM ="ACCEPTED_DATE";
    static final String FUTURE_DATE_PARAM ="accepted_FUTURE_DATE";
    static final String RENTED_COPY_PARAM ="rentedCopies";
}
