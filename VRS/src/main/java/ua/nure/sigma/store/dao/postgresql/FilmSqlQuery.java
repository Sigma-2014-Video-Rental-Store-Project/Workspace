package ua.nure.sigma.store.dao.postgresql;

/**
 * Created by vlad on 14.11.14.
 */
public interface FilmSqlQuery  {
    static final String SQL_SELECT_FROM_FILM_BY_ID =
            "Select films.ID ,TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT, coalesce(rented.rentedCopies, 0) as rentedCp from films LEFT OUTER JOIN (SELECT film_id, sum(count) as rentedCopies FROM FILM_AT_RENT WHERE ACCEPTED_DATE IS NULL group by film_ID) rented on rented.film_ID = films.ID WHERE FILMS.ID = ? ";
    static final String SQL_SELECT_FROM_FILMS_ALL_FILM =
            "Select films.ID ,TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT, coalesce(rented.rentedCopies,  0) as rentedCp from films LEFT OUTER JOIN (SELECT film_ID, sum(count) as rentedCopies FROM FILM_AT_RENT WHERE ACCEPTED_DATE IS NULL group by film_ID) rented on rented.film_ID = films.ID";
    static final String SQL_INSERT_INTO_FILMS = "INSERT INTO FILMS (ID, TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT) VALUES(DEFAULT,?,?,?,?,?,?,?,?)  RETURNING ID";
    static final String SQL_UPDATE_FILM =
            "UPDATE FILMS SET TITLE = ?, YEAR =?, DESCRIPTION =?, COVER =?, AMOUNT =?, GENERAL_PRICE = ?, RENT_PRICE = ?, BONUS_FOR_RENT = ? WHERE ID = ?";
    static final String SQL_DELETE_FILM = "DELETE FROM FILMS WHERE ID = ?";

    static final String SQL_FILMS_CURRENT_ID = "select lastvalue from films_id_seq)";
}
