package ua.nure.sigma.store.dao.postgresql;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmRentedDAO;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.entity.FilmForRent;
import ua.nure.sigma.store.exeption.TooManyCopiesForReturnException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vlad on 16.10.14.
 */
public class PostgreSqlFimsRentedDAO implements FilmRentedDAO, FilmRentedSqlQuery {

     private static final Logger LOG = Logger.getLogger(PostgreSqlFimsRentedDAO.class);

    private FilmForRent extractFilm(ResultSet rs) throws SQLException {
        FilmForRent film = new FilmForRent();
        film.setFilmID(rs.getInt(FILM_ID_PARAM));
        film.setCopies(rs.getInt(COUNT_PARAM));
        film.setCopiesLeft(rs.getInt(COPIES_LEFT_PARAM));
        film.setAcceptedDate(rs.getDate(ACCEPTED_DATE_PARAM));
        film.setFutureDate(rs.getDate(FUTURE_DATE_PARAM));
        return film;
    }

    public List<FilmForRent> findFilmRentedByRentID(long rentID, Connection connection) {
        List<FilmForRent> rents = new ArrayList<FilmForRent>();
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            pstmnt = connection.prepareStatement(SQL_SELECT_FROM_FILM_RENTED_BY_RENT_ID);
            pstmnt.setLong(1, rentID);
            rs = pstmnt.executeQuery();
            while (rs.next()) {
                rents.add(extractFilm(rs));
            }
        } catch (Exception e) {
            LOG.error("Can not obtain RENTED FILMS by RENT_ID.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
        }
        return rents;
    }

    @Override
    public int findFilmRentLeftCopies(long rentID, int filmID){
        int copiesLeft = 0;
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            copiesLeft = findFilmRentLeftCopies(rentID,filmID,connection);

        } catch (Exception e) {
            LOG.error("Can not obtain rented film by login.", e);
        } finally {
            DAOFactory.commitAndClose(connection);
        }
       return  copiesLeft;
    }
    public int findFilmRentLeftCopies(long rentID, int filmID, Connection connection){
        int copiesLeft = 0;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            pstmnt = connection.prepareStatement(SQL_SELECT_FILM_RENTED);
            pstmnt.setLong(1, rentID);
            pstmnt.setInt(2, filmID);
            rs = pstmnt.executeQuery();
            if (rs.next()){
                copiesLeft = rs.getInt(COPIES_LEFT_PARAM);
            }
        } catch (Exception e) {
//            LOG.error("Can not obtain User by login.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
        }
        return copiesLeft;
    }
    @Override
    public List<FilmForRent> findFilmRentedByRentID(long rentID) {
        List<FilmForRent> rents = new ArrayList<FilmForRent>();
        Connection connection = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            rents = findFilmRentedByRentID(rentID,connection);
        } catch (Exception e) {
            LOG.error("Can not obtain Rented films by rentID.", e);
        } finally {
            DAOFactory.commitAndClose(connection);
        }
        return rents;
    }

    public void createFilmsRented(long rentID,FilmForRent forRents, Connection connection) throws Exception {
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            pstmnt = connection.prepareStatement(SQL_INSERT_INTO_FILM_AT_RENTED);
            pstmnt.setInt(1, forRents.getFilmID());
            pstmnt.setLong(2, rentID);
            pstmnt.setInt(3,forRents.getCopies());
            pstmnt.setDate(4, new java.sql.Date(forRents.getFutureDate().getTime()));
            pstmnt.setInt(5,forRents.getCopies());
            pstmnt.execute();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
            LOG.error("Can not create rented film entity", e);
            throw new Exception();
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
        }
    }
    @Override
    public void createFilmsRented(long rentID, List<FilmForRent> forRents, Connection connection) throws Exception {
        for (FilmForRent f:forRents){
            createFilmsRented(rentID,f,connection);
        }
    }

    @Override
    public int findFilmCopiesAtRent(int filmID) {
        int copies = 0;
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(SQL_SELECT_FROM_FILM_AT_RENT_BY_COPIES_AT_RENT);
            rs = preparedStatement.executeQuery();
            if (rs.next()){
                copies = rs.getInt(RENTED_COPY_PARAM);
            }

        } catch (Exception e) {
//            LOG.error("Can not obtain User by login.", e);
        } finally {
            DAOFactory.close(preparedStatement);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }
        return copies;
    }

    @Override
    public void updateFilmRent(long rentID, int filmID, int copies) throws Exception {
        Connection connection = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            int copiesLeft = findFilmRentLeftCopies(rentID,filmID);
            if (copiesLeft == copies){
                closeFilmRent(rentID,filmID, connection);
            }else  if (copies < copiesLeft){
                updateFilmRent(rentID,filmID,copies,copiesLeft,connection);
            }else {
                throw new TooManyCopiesForReturnException("Copies left less than Copies for return");
            }

        } catch (Exception e) {
            DAOFactory.rollback(connection);
            throw e;
//            LOG.error("Can not obtain User by login.", e);
        } finally {

            DAOFactory.commitAndClose(connection);
        }
    }
    @Override
    public void updateFilmRent(long rentID, int filmID, int copies, int copiesLeft,Connection connection) {
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            pstmnt = connection.prepareStatement(SQL_UPDATE_FILM_RENTED);
            pstmnt.setInt(1, copiesLeft-copies);
            pstmnt.setLong(2, rentID);
            pstmnt.setInt(3, filmID);
            pstmnt.execute();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not obtain User by login.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
        }
    }

    @Override
    public void closeFilmRent(long rentID, int filmID) {
        Connection connection = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            closeFilmRent(rentID,filmID,connection);
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not obtain User by login.", e);
        } finally {
            DAOFactory.commitAndClose(connection);
        }
    }
    public void closeFilmRent(long rentID, int filmID, Connection connection) {
        PreparedStatement pstmnt = null;
        try {
            pstmnt = connection.prepareStatement(SQL_CLOSE_FILM_RENT);
            pstmnt.setLong(1, rentID);
            pstmnt.setInt(2, filmID);
            pstmnt.execute();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
            LOG.error("Can not close order.", e);
        } finally {
            DAOFactory.close(pstmnt);
        }
    }
    @Override
    public void closeFilmRent(long rentID) {
        Connection connection = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            List<FilmForRent> forRents = findFilmRentedByRentID(rentID,connection);
            for (FilmForRent rent : forRents){
                closeFilmRent(rentID,rent.getFilmID(),connection);
            }
        }catch (Exception e){
            DAOFactory.rollback(connection);
        }finally {
            DAOFactory.commitAndClose(connection);
        }
    }


}
