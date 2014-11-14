package ua.nure.sigma.store.dao.postgresql;

/**
 * Created by vlad on 14.11.14.
 */
public interface AdminSqlQuery {

    static final String SQL_SELECT_FROM_ADMINS_BY_EMAIL =
            "SELECT * FROM ADMINS WHERE ADMIN_EMAIL = ?";
    static final String SQL_SELECT_FROM_ADMINS_BY_ID = "SELECT * FROM ADMINS WHERE ADMIN_ID = ?";
    static final String SQL_SELECT_FROM_ADMINS_ALL_ADMIN = "SELECT * FROM ADMINS";
    static final String SQL_INSERT_INTO_ADMINS = "INSERT INTO ADMINS(admin_id,role_id,admin_email,password_hash,locale_ID" +
            ") VALUES(DEFAULT,?,?,?,?)";
    static final String SQL_UPDATE_ADMIN_PASSWORD = "UPDATE ADMINS SET PASSWORD_HASH = ? WHERE ADMIN_ID = ?";
    static final String SQL_UPDATE_ADMIN_LOCALE = "UPDATE ADMINS SET LOCALE_ID = ? WHERE ADMIN_ID = ?";
    static final String SQL_DELETE_ADMIN = "DELETE FROM ADMINS WHERE ADMIN_ID = ?";
}
