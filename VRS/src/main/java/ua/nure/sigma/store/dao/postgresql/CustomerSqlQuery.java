package ua.nure.sigma.store.dao.postgresql;

/**
 * Created by vlad on 16.11.14.
 */
public interface CustomerSqlQuery {
    static final String SQL_SELECT_FROM_CUSTOMERS_BY_ID = "SELECT * FROM CUSTOMERS WHERE CUSTOMER_ID = ?";
    static final String SQL_SELECT_FROM_CUSTOMERS_ALL_CUSTOMER = "SELECT * FROM CUSTOMERS";
    static final String SQL_INSERT_INTO_CUSTOMERS = "INSERT INTO CUSTOMERS (customer_id,LAST_NAME , FIRST_NAME , MIDLE_NAME , CUSTOMER_EMAIL, CUSTOMER_PHONE , SEX_ID, CUSTOMER_PHOTO, BONUS) VALUES(DEFAULT,?,?,?,?,?,?,?,?) RETURNING CUSTOMER_ID";
    static final String SQL_UPDATE_CUSTOMERS =
            "UPDATE CUSTOMERS SET LAST_NAME = ?, FIRST_NAME =?, MIDLE_NAME =?, CUSTOMER_EMAIL =?, CUSTOMER_PHONE =?, SEX_ID =?, CUSTOMER_PHOTO=?, BONUS = ? WHERE CUSTOMER_ID = ?";
    static final String SQL_DELETE_CUSTOMER = "DELETE FROM CUSTOMERS WHERE CUSTOMER_ID = ?";

    static final String CUSTOMER_ID_PARAM = "CUSTOMER_ID";
    static final String FIRST_NAME_PARAM = "FIRST_NAME";
    static final String LAST_NAME_PARAM = "LAST_NAME";
    static final String MIDLE_NAME_PARAM = "MIDLE_NAME";
    static final String EMAIL_PARAM = "CUSTOMER_EMAIL";
    static final String PHONE_PARAM = "CUSTOMER_PHONE";
    static final String SEX_ID_PARAM = "SEX_ID";
    static final String PHOTO_PARAM = "CUSTOMER_PHOTO";
    static final String BONUS_PARAM = "BONUS";


}
