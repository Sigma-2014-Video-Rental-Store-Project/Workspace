package ua.nure.sigma.store.dao.postgresql;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.LocaleDAO;
import ua.nure.sigma.store.entity.Locale;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 07.11.14.
 */
public class PostgreSqlLocaleDAO implements LocaleDAO, LocaleSqlQuery {

    private static final Logger LOG = Logger
            .getLogger(PostgreSqlLocaleDAO.class);
    @Override
    public int findLocaleIdByName(String locale) {
        int id = 0;
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection
                    .prepareStatement(SQL_SELECT_FROM_LOCALE_BY_NAME);
            pstmnt.setString(1, locale);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                id = rs.getInt(LOCALE_ID_PARAM);
            }
        } catch (Exception e) {
            LOG.error("Can not obtain Locale by name.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }
        return id;
    }

    @Override
    public String findLocaleNameById(int id) {
        String locale = null;
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection
                    .prepareStatement(SQL_SELECT_FROM_LOCALE_BY_ID);
            pstmnt.setInt(1, id);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                locale = rs.getString(LOCALE_NAME_PARAM);
            }
        } catch (Exception e) {
            LOG.error("Can not obtain Locale by id.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }
        return locale;
    }

    @Override
    public List<Locale> findAlLocale() {
        List<Locale> localeList = new ArrayList<Locale>();
        Connection connection = null;
        Statement stmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(SQL_SELECT_FROM_LOCALE);
            while (rs.next()) {
                localeList.add(new Locale(rs.getInt(LOCALE_ID_PARAM),rs.getString(LOCALE_NAME_PARAM)));
            }
        } catch (Exception e) {
            LOG.error("Can not obtain all locale", e);
        } finally {
            DAOFactory.close(stmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }

        return localeList;
    }
}
