package ua.nure.sigma.store.dao.postgresql;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.SexDAO;
import ua.nure.sigma.store.entity.Role;
import ua.nure.sigma.store.entity.Sex;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public class PostgreSqlSexDAO implements SexDAO{

    private static final String SQL_SELECT_FROM_SEX_BY_ID = "SELECT * FROM SEX WHERE SEX_ID = ?";
    private static final String SQL_SELECT_FROM_SEX_ALL_SEX = "SELECT * FROM SEX";
    private static final String SQL_SELECT_FROM_SEX_BY_NAME =
            "SELECT * FROM SEX WHERE SEX = ?";
    private static final Logger LOG = Logger
            .getLogger(PostgreSqlSexDAO.class);

    private Sex extractSex(ResultSet rs) throws SQLException {
        Sex sex = new Sex();
        sex.setSexID(rs.getInt("SEX_ID"));
        sex.setSexName(rs.getString("SEX"));
        return sex;
    }

    @Override
    public Sex findSexByID(int id) {
        Sex sex = null;
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection.prepareStatement(SQL_SELECT_FROM_SEX_BY_ID);
            pstmnt.setInt(1, id);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                sex = extractSex(rs);
            }
        } catch (Exception e) {
//            DAOFactory.rollback(connection);
//            LOG.error("Can not obtain User by id.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }

        return sex;
    }

    @Override
    public List<Sex> findAllSex() {
        List<Sex> admin = new ArrayList<Sex>();
        Connection connection = null;
        Statement stmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(SQL_SELECT_FROM_SEX_ALL_SEX);
            while (rs.next()) {
                admin.add(extractSex(rs));
            }
        } catch (Exception e) {
//            LOG.error("Can not obtain User by login.", e);
        } finally {
            DAOFactory.close(stmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }
        return admin;
    }

    @Override
    public Sex findSexIDBySexName(String sex) {
        Sex sex1 = null;
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection
                    .prepareStatement(SQL_SELECT_FROM_SEX_BY_NAME);
            pstmnt.setString(1, sex);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                sex1 = extractSex(rs);
            }
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not obtain User by login.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }
        return sex1;
    }
}
