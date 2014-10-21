package ua.nure.sigma.store.dao.postgresql;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmDAO;
import ua.nure.sigma.store.entity.Film;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public class PostgreSqlFilmDAO implements FilmDAO {

    private static final String SQL_SELECT_FROM_FILM_BY_ID = "Select films.FILM_ID ,TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT, coalesce(rented.rentedCopies, FILMS.AMOUNT) as copiesLeft from films LEFT OUTER JOIN (SELECT film_id, sum(count) as rentedCopies FROM FILM_AT_RENT WHERE ACCEPTED_DATE IS NULL group by film_id) rented on rented.film_id = films.film_id WHERE FILMS.FILM_ID = ?";
    private static final String SQL_SELECT_FROM_FILMS_ALL_FILM =
            "Select films.FILM_ID ,TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT, coalesce(rented.rentedCopies,  FILMS.AMOUNT) as copiesLeft from films LEFT OUTER JOIN (SELECT film_id, sum(count) as rentedCopies FROM FILM_AT_RENT WHERE ACCEPTED_DATE IS NULL group by film_id) rented on rented.film_id = films.film_id";
    private static final String SQL_INSERT_INTO_FILMS = "INSERT INTO FILMS (TITLE, YEAR, DESCRIPTION, COVER, AMOUNT, GENERAL_PRICE, RENT_PRICE, BONUS_FOR_RENT) VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_FILM =
            "UPDATE FILMS SET TITLE = ?, YEAR =?, DESCRIPTION =?, COVER =?, AMOUNT =?, GENERAL_PRICE = ?, RENT_PRICE = ?, BONUS_FOR_RENT = ? WHERE FILM_ID = ?";
    private static final String SQL_DELETE_FILM = "DELETE FROM FILMS WHERE FILM_ID = ?";

    private Film extractFilm(ResultSet rs) throws SQLException {
        Film film = new Film();
        film.setFilmId(rs.getInt("FILM_ID"));
        film.setTitle(rs.getString("TITLE"));
        film.setYear(rs.getInt("YEAR"));
        film.setDescription(rs.getString("DESCRIPTION"));
        film.setCover(rs.getString("COVER"));
        film.setAmount(rs.getInt("AMOUNT"));
        film.setGeneralPrice(rs.getLong("GENERAL_PRICE"));
        film.setRentPrice(rs.getLong("RENT_PRICE"));
        film.setBonusForRent(rs.getLong("BONUS_FOR_RENT"));
        film.setCopiesLeft(film.getAmount()- rs.getInt("rentedCp"));
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
//            DAOFactory.rollback(connection);
//            LOG.error("Can not obtain User by login.", e);
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
            DAOFactory.rollback(connection);
//            LOG.error("Can not obtain User by id.", e);
        } finally {
            DAOFactory.commitAndClose(connection);
        }
        return film;
    }

    public Film findFilmById(Connection connection, int id) {
        Film film = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        try {
            pstmnt = connection.prepareStatement(SQL_SELECT_FROM_FILM_BY_ID);
            pstmnt.setInt(1, id);
            rs = pstmnt.executeQuery();
            if (rs.next()) {
                film = extractFilm(rs);
            }
        } catch (Exception e) {

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
    public void createFilm(Film film) {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection.prepareStatement(SQL_INSERT_INTO_FILMS);
            int position = 1;
            //pstmnt.setString(position++, "DEFAULT");
            setupPrepareStatement(pstmnt, film, position);
            pstmnt.execute();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not add new client.", e);
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
            e.printStackTrace();
            DAOFactory.rollback(connection);
//            LOG.error("Can not update User's block.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.commitAndClose(connection);
        }
    }

    @Override
    public void deleteFilm(Film film) {
        deleteFilm(film.getFilmId());
    }

    @Override
    public void deleteFilm(int filmId) {
        Connection connection = null;
        PreparedStatement pstmnt = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection.prepareStatement(SQL_DELETE_FILM);
            int position = 1;
            pstmnt.setInt(position, filmId);
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
