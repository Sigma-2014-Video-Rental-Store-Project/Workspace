package ua.nure.sigma.store.dao.postgresql;

import org.apache.log4j.Logger;
import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmCategoryDAO;
import ua.nure.sigma.store.dao.FilmDAO;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public class PostgreSqlFilmDAO implements FilmDAO, FilmSqlQuery {

    private static final String SQL_DEBT_FILM = "SELECT rent_id FROM FILM_AT_RENT WHERE film_id =? AND accepted_date is not null";
    private static final Logger LOG = Logger
            .getLogger(PostgreSqlFilmDAO.class);

    private Film extractFilm(ResultSet rs) throws SQLException {
        Film film = new Film();
        film.setFilmId(rs.getInt(FILM_ID_PARAM));
        film.setTitle(rs.getString(TITLE_PARAM));
        film.setYear(rs.getInt(YEAR_PARAM));
        film.setDescription(rs.getString(DESCRIPTION_PARAM));
        film.setCover(rs.getString(COVER_PARAM));
        film.setAmount(rs.getInt(AMOUNT_PARAM));
        film.setGeneralPrice(rs.getLong(GENERAL_PRICE_PARAM));
        film.setRentPrice(rs.getLong(RENT_PRICE_PARAM));
        film.setBonusForRent(rs.getLong(BONUS_FOR_RENT_PARAM));
        film.setCopiesLeft(film.getAmount()- rs.getInt(RENTED_COPY_PARAM));
        return film;
    }

    @Override
    public List<Film> findAllFilms() {
        List<Film> admin = new ArrayList<Film>();
        Connection connection = null;
        Statement stmnt = null;
        ResultSet rs = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            stmnt = connection.createStatement();
            rs = stmnt.executeQuery(SQL_SELECT_FROM_FILMS_ALL_FILM);
            while (rs.next()) {
                admin.add(extractFilm(rs));
            }
        } catch (Exception e) {
            LOG.error("Can not obtain Films.", e);
        } finally {
            DAOFactory.close(stmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }

        return admin;
    }

    @Override
    public Film findFilmById(int id) {
        Film film = null;
        Connection connection = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            film = findFilmById(connection, id);
        } catch (Exception e) {
            LOG.error("Can not obtain Film by id.", e);
        } finally {
            DAOFactory.commitAndClose(connection);
        }
        return film;
    }

    public Film findFilmById(Connection connection, int id) throws Exception{
        Film film = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            int position = 1;
            pstmnt = connection.prepareStatement(SQL_SELECT_FROM_FILM_BY_ID);
            pstmnt.setInt(position, id);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                film = extractFilm(rs);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
        }
        return film;
    }

    int setupPrepareStatement(PreparedStatement pstmnt, Film film, int position) throws SQLException {
        pstmnt.setString(position++, film.getTitle());
        pstmnt.setInt(position++, film.getYear());
        pstmnt.setString(position++, film.getDescription());
        pstmnt.setString(position++, film.getCover());
        pstmnt.setInt(position++, film.getAmount());
        pstmnt.setLong(position++, film.getGeneralPrice());
        pstmnt.setLong(position++, film.getRentPrice());
        pstmnt.setLong(position++, film.getBonusForRent());
        return position;
    }

    @Override
    public void createFilm(Film film, Connection connection) throws Exception {
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            pstmnt = connection.prepareStatement(SQL_INSERT_INTO_FILMS);
            int position = 1;
            setupPrepareStatement(pstmnt, film, position);
            rs = pstmnt.executeQuery();
            if (rs.next()){
                film.setFilmId(rs.getInt(1));
            }
        } catch (Exception e) {
            DAOFactory.rollback(connection);
            LOG.error("Can not add new Film.", e);
            throw e;
        } finally {
            DAOFactory.close(rs);
            DAOFactory.close(pstmnt);
        }
    }

    @Override
    public void createFilmWithCategories(Film film, List<Category> categoryList) {
        Connection connection = null;
        PreparedStatement pstmnt = null;

        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            createFilm(film,connection);
            FilmCategoryDAO categoryDAO = DAOFactory.getInstance().getFilmCategoryDAO();
            for (Category category : categoryList){
                categoryDAO.createFilmCategory(film,category,connection);
            }

        } catch (Exception e) {
            DAOFactory.rollback(connection);
            LOG.error("Can not add new film with categories.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.commitAndClose(connection);
        }
    }

    @Override
    public void updateFilm(Film film) {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection.prepareStatement(SQL_UPDATE_FILM);
            int position = 1;
            position = setupPrepareStatement(pstmnt, film, position);
            pstmnt.setInt(position, film.getFilmId());
            pstmnt.executeUpdate();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
            LOG.error("Can not update Film's block.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.commitAndClose(connection);
        }
    }


    @Override
    public void deleteFilm(int filmId) throws Exception {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            if (checkDebt(connection,filmId)) throw new Exception();
            pstmnt = connection.prepareStatement(SQL_DELETE_FILM);
            int position = 1;
            pstmnt.setInt(position, filmId);
            pstmnt.executeUpdate();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
            LOG.error("Can not delete Film's block.", e);
            throw e;
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.commitAndClose(connection);
        }
    }

    private boolean checkDebt(Connection connection, int filmID){
        PreparedStatement pstm = null;
        ResultSet rs = null;
        boolean b = false;
        try {
            pstm = connection.prepareStatement(SQL_DEBT_FILM);
            pstm.setInt(1,filmID);
            rs = pstm.executeQuery();
            if (rs.next())  b = true;
        }   catch (Exception e){
            LOG.debug("checkDebt for film exception");
        }   finally {
                DAOFactory.close(pstm);
                DAOFactory.close(rs);
        }
        return b;
    }

}
