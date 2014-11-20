package ua.nure.sigma.store.dao.postgresql;

/**
 * Created by vlad on 16.11.14.
 */
public interface RoleSqlQuery {
    static final String SQL_SELECT_FROM_ROLE_BY_NAME =
            "SELECT * FROM ROLES WHERE ROLE_NAME = ?";
    static final String SQL_SELECT_FROM_ROLE_BY_ID = "SELECT * FROM ROLES WHERE ROLE_ID = ?";
    static final String SQL_SELECT_FROM_ROLE_ALL_ROLE = "SELECT * FROM ROLES";

    static final String ROLE_ID_PARAM = "ROLE_ID";
    static final String ROLE_NAME_PARAM = "ROLE_NAME";

}
