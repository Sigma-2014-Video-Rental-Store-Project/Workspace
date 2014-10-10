package ua.nure.sigma.store.dao.postgresql;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmCategoryDAO;
import ua.nure.sigma.store.dao.FilmDAO;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public class PostgreSqlFilmCategoryDAO implements FilmCategoryDAO{

    private static final String SQL_SELECT_FROM_ADMINS_BY_EMAIL =
            "SELECT * FROM ADMINS WHERE ADMIN_EMAIL = ?";
    private static final String SQL_SELECT_FROM_FILM_CATEGORY_BY_CATEGORY_ID = "SELECT FILM_ID FROM FILM_CATEGORIES WHERE CATEGORY_ID = ?";
    private static final String SQL_SELECT_FROM_FILMS_ALL_FILM = "SELECT * FROM FILMS";
    private static final String SQL_INSERT_INTO_FILMS = "INSERT INTO FILMS VALUES(?,?,?,?,?,?,?,?)";
    private static final String SQL_UPDATE_FILM =
            "UPDATE FILMS SET TITLE = ?, YEAR =?, DESCRIPTION =?, COVER =?, AMOUNT =?, GENERAL_PRICE =?, RENT_PRICE =?, BONUS_FOR_RENT =? WHERE FILM_ID = ?";
    private static final String SQL_DELETE_FILM = "DELETE FROM FILMS WHERE FILM_ID = ?";



    @Override
    public List<Film> findFilmsByCategoryID(int id) {
        List<Film> films = new ArrayList<Film>();
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        FilmDAO filmDAO = DAOFactory.getInstance().getFilmDAO();
        try {
            connection = DAOFactory.getConnection();
            pstmnt = connection.prepareStatement(SQL_SELECT_FROM_FILM_CATEGORY_BY_CATEGORY_ID);
            pstmnt.setInt(1, id);
            rs = pstmnt.executeQuery();
            while(rs.next()) {
               films.add(filmDAO.findFilmByID(connection,rs.getInt("FILM_ID")));
            }
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not obtain User by id.", e);
        } finally {
            DAOFactory.commitAndClose(connection);
        }
        return films;
    }

    @Override
    public void createFilmCategory(Film film, Category category, Connection connection) throws SQLException {

    }

    @Override
    public void createFilmCategories(Film film, List<Category> categoryList, Connection connection) throws SQLException {

    }

    @Override
    public void updateFilmCategories(Film film, List<Category> categoryList, Connection connection) throws SQLException {

    }

    @Override
    public void updateFilmCategories(Film film, List<Category> categoryList) {

    }

    @Override
    public void createFilmCategory(Film film, Category category) {

    }

    @Override
    public void createFilmCategories(Film film, List<Category> categoryList) {

    }

    @Override
    public void deleteFilmCategories(Film film) {

    }

    @Override
    public void deleteFilmCategories(Film film, Connection connection) throws SQLException {

    }
}
