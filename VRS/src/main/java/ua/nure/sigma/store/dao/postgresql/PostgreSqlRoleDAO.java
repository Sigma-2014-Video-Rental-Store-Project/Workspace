package ua.nure.sigma.store.dao.postgresql;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.RoleDAO;
import ua.nure.sigma.store.entity.Role;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by nikolaienko on 07.10.14.le
 */
public class PostgreSqlRoleDAO implements RoleDAO{

    private static final String SQL_SELECT_FROM_ROLE_BY_NAME =
            "SELECT * FROM ROLES WHERE ROLE_NAME = ?";
    private static final String SQL_SELECT_FROM_ROLE_BY_ID = "SELECT * FROM ROLES WHERE ROLE_ID = ?";

    private Role extractRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt("ROLE_ID"));
        role.setName(rs.getString("ROLE_NAME"));
        return role;
    }

    @Override
    public Role findRoleByID(int id) {
        Role role = null;
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            pstmnt = connection
                    .prepareStatement(SQL_SELECT_FROM_ROLE_BY_ID);
            pstmnt.setInt(1, id);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                role = extractRole(rs);
            }
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not obtain User by login.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }
        return role;
    }

    @Override
    public Role findRoleByName(String name) {
        Role role = null;
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            pstmnt = connection
                    .prepareStatement(SQL_SELECT_FROM_ROLE_BY_NAME);
            pstmnt.setString(1, name);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                role = extractRole(rs);
            }
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not obtain User by login.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }
        return role;
    }
}
