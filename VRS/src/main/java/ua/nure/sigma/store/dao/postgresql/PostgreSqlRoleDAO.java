package ua.nure.sigma.store.dao.postgresql;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.RoleDAO;
import ua.nure.sigma.store.entity.Role;
import ua.nure.sigma.store.entity.Sex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.le
 */
public class PostgreSqlRoleDAO implements RoleDAO, RoleSqlQuery{

    private static final Logger LOG = Logger
            .getLogger(PostgreSqlRoleDAO.class);

    private Role extractRole(ResultSet rs) throws SQLException {
        Role role = new Role();
        role.setId(rs.getInt(ROLE_ID_PARAM));
        role.setName(rs.getString(ROLE_NAME_PARAM));
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
            connection.setAutoCommit(false);
            pstmnt = connection
                    .prepareStatement(SQL_SELECT_FROM_ROLE_BY_ID);
            pstmnt.setInt(1, id);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                role = extractRole(rs);
            }
        } catch (Exception e) {
            LOG.error("Can not obtain Role by id.", e);
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
            connection.setAutoCommit(false);
            pstmnt = connection
                    .prepareStatement(SQL_SELECT_FROM_ROLE_BY_NAME);
            pstmnt.setString(1, name);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                role = extractRole(rs);
            }
        } catch (Exception e) {
            LOG.error("Can not obtain Role by name.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }
        return role;
    }

    @Override
    public List<Role> findAllRole() {
        List<Role> roleList = new ArrayList<Role>();
        Connection connection = null;
        Statement stmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(SQL_SELECT_FROM_ROLE_ALL_ROLE);
            while (rs.next()) {
                roleList.add(extractRole(rs));
            }
        } catch (Exception e) {
            LOG.error("Can not obtain All role.", e);
        } finally {
            DAOFactory.close(stmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }
        return roleList;
    }
}
