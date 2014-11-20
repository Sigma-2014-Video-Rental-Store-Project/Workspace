package ua.nure.sigma.store.dao.postgresql;

/**
 * Created by vlad on 16.11.14.
 */
public interface RentSqlQuery {
    static final String SQL_SELECT_FROM_RENTS_CUSTOMER_BY_RENT_ID = "SELECT * FROM RENT WHERE RENT_ID = ?";
    static final String SQL_SELECT_FROM_RENTS_RENTS__BY_CUSTOMER_ID = "SELECT * FROM RENT WHERE CUSTOMER_ID =?";
    static final String SQL_INSERT_INTO_RENTS = "INSERT INTO RENT(RENT_ID, CUSTOMER_ID, RENTED_DATE)  VALUES(DEFAULT,?,CURRENT_DATE) RETURNING RENT_ID, RENTED_DATE";

    static final String CUSTOMER_ID_PARAM ="CUSTOMER_ID";
    static final String RENT_ID_PARAM = "RENT_ID";
    static final String RENTED_DATE_PARAM = "RENTED_DATE";
}

