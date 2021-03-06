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
public class PostgreSqlSexDAO implements SexDAO,SexSqlQuery{


    private static final Logger LOG = Logger
            .getLogger(PostgreSqlSexDAO.class);

    private Sex extractSex(ResultSet rs) throws SQLException {
        Sex sex = new Sex();
        sex.setSexID(rs.getInt(SEX_ID_PARAM));
        sex.setSexName(rs.getString(SEX_NAME_PARAM));
        return sex;
    }

    @Override
    public Sex findSexByID(int id, int locale) {
        Sex sex = null;
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection.prepareStatement(SQL_SELECT_FROM_SEX_BY_ID);
            pstmnt.setInt(1, id);
            pstmnt.setInt(2, locale);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                sex = extractSex(rs);
            }
            return sex;
        } catch (Exception e) {
            LOG.error("Can not obtain all Sex.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }

        return sex;
    }

    @Override
    public List<Sex> findAllSex(int locale) {
        List<Sex> admin = new ArrayList<Sex>();
        Connection connection = null;
        PreparedStatement stmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            stmnt = connection.prepareStatement(SQL_SELECT_FROM_SEX_ALL_SEX);
            stmnt.setInt(1,locale);
            rs = stmnt.executeQuery();
            while (rs.next()) {
                admin.add(extractSex(rs));
            }
            return admin;
        } catch (Exception e) {
            LOG.error("Can not obtain All sex by locale", e);
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
            return sex1;
        } catch (Exception e) {
            LOG.error("Can not obtain Sex by name.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }
        return sex1;
    }
}
