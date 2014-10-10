package ua.nure.sigma.store.dao.postgresql;

import ua.nure.sigma.store.dao.AdminDAO;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.entity.Admin;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public class PosgreSqlAdminDAO implements AdminDAO{

//   private static final Logger LOG = Logger.getLogger(DerbyUserDAO.class);

    private static final String SQL_SELECT_FROM_ADMINS_BY_EMAIL =
            "SELECT * FROM ADMINS WHERE ADMIN_EMAIL = ?";
    private static final String SQL_SELECT_FROM_ADMINS_BY_ID = "SELECT * FROM ADMINS WHERE ADMIN_ID = ?";
    private static final String SQL_SELECT_FROM_ADMINS_ALL_ADMIN = "SELECT * FROM ADMINS";
    private static final String SQL_INSERT_INTO_ADMINS = "INSERT INTO ADMINS VALUES(?, ?, ?)";
    private static final String SQL_UPDATE_ADMIN_PASSWORD = "UPDATE ADMINS SET PASSWORD_HESH = ? WHERE ADMIN_ID = ?";
    private static final String SQL_DELETE_ADMIN = "DELETE FROM ADMINS WHERE ADMIN_ID = ?";

    /**
     * Extracts User bean from ResultSet object.
     *
     * @param rs
     *            ResultSet object after query execution.
     * @return fulfilled User bean.
     * @throws SQLException
     *             caused by troubles connected with field extraction.
     */
    private Admin extractAdmin(ResultSet rs) throws SQLException {
        Admin admin = new Admin();
        admin.setId(rs.getInt("ADMIN_ID"));
        admin.setEmail(rs.getString("ADMIN"));
        admin.setPassword(rs.getInt("password"));
        admin.setRoleId(rs.getInt("role_id"));
        return admin;
    }


    @Override
    public Admin findAdminByLogin(String login) {
        Admin admin = null;
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            pstmnt = connection
                    .prepareStatement(SQL_SELECT_FROM_ADMINS_BY_EMAIL);
            pstmnt.setString(1, login);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                admin = extractAdmin(rs);
            }
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not obtain User by login.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }

        return admin;
    }

    @Override
    public Admin findAdminById(int id) {
        Admin admin = null;
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            pstmnt = connection.prepareStatement(SQL_SELECT_FROM_ADMINS_BY_ID);
            pstmnt.setInt(1, id);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                admin = extractAdmin(rs);
            }
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not obtain User by id.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }

        return admin;
    }

    @Override
    public List<Admin> findAllAdmins() {
        List<Admin> admin = new ArrayList<Admin>();
        Connection connection = null;
        Statement stmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(SQL_SELECT_FROM_ADMINS_ALL_ADMIN);
            while (rs.next()) {
                admin.add(extractAdmin(rs));
            }
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not obtain User by login.", e);
        } finally {
            DAOFactory.close(stmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }

        return admin;
    }

    @Override
    public List<Admin> findAllAdmin(Connection connection) throws SQLException {
        return null;
    }

    @Override
    public void createAdmin(Admin admin) {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        try {
            connection = DAOFactory.getConnection();
            pstmnt = connection.prepareStatement(SQL_INSERT_INTO_ADMINS);
            int position = 1;
            pstmnt.setInt(position++, admin.getRoleId());
            pstmnt.setString(position++, admin.getEmail());
            pstmnt.setInt(position++, admin.getPassword());
            pstmnt.executeUpdate();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not add new client.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.commitAndClose(connection);
        }
    }

    @Override
    public void updateAdminPassword(Admin admin) {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        try {
            connection = DAOFactory.getConnection();
            pstmnt = connection.prepareStatement(SQL_UPDATE_ADMIN_PASSWORD);
            int position = 1;
            pstmnt.setInt(position++, admin.getPassword());
            pstmnt.setInt(position, admin.getId());
            pstmnt.executeUpdate();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not update User's block.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.commitAndClose(connection);
        }
    }

    @Override
    public void deleteAdmin(Admin admin) {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        try {
            connection = DAOFactory.getConnection();
            pstmnt = connection.prepareStatement(SQL_DELETE_ADMIN);
            int position = 1;
            pstmnt.setInt(position, admin.getId());
            pstmnt.executeUpdate();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not update User's block.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.commitAndClose(connection);
        }
    }

}
