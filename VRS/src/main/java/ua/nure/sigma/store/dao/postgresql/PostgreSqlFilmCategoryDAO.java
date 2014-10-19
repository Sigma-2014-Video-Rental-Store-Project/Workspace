package ua.nure.sigma.store.dao.postgresql;

import ua.nure.sigma.store.dao.CategoryDAO;
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
public class PostgreSqlFilmCategoryDAO implements FilmCategoryDAO {

    private static final String SQL_SELECT_FROM_FILM_CATEGORIES_CATEGORIES_BY_FILM_ID =
            "SELECT * FROM FILM_CATEGORIES WHERE FILM_ID = ?";
    private static final String SQL_SELECT_FROM_FILM_CATEGORY_BY_CATEGORY_ID = "SELECT FILM_ID FROM FILM_CATEGORIES WHERE CATEGORY_ID = ?";
    private static final String SQL_INSERT_INTO_FILM_CATEGORIES = "INSERT INTO FILM_CATEGORIES VALUES(?,?)";
    private static final String SQL_DELETE_FILM_CATEGORIES = "DELETE FROM FILM_CATEGORIES WHERE FILM_ID = ?";

    @Override
    public List<Film> findFilmsByCategoryID(int id) {
        List<Film> films = new ArrayList<Film>();
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        FilmDAO filmDAO = DAOFactory.getInstance().getFilmDAO();
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection.prepareStatement(SQL_SELECT_FROM_FILM_CATEGORY_BY_CATEGORY_ID);
            pstmnt.setInt(1, id);
            rs = pstmnt.executeQuery();
            while (rs.next()) {
                films.add(filmDAO.findFilmById(connection, rs.getInt("FILM_ID")));
            }
        } catch (Exception e) {           
//            LOG.error("Can not obtain User by id.", e);
        } finally {
	        DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }
        return films;
    }

    @Override
    public List<Category> findCategoriesByFilmID(int id) {
        List<Category> categories = new ArrayList<Category>();
        Connection connection = null;
        PreparedStatement pstmnt = null;
        ResultSet rs = null;
        CategoryDAO categoryDAO = DAOFactory.getInstance().getCategoryDAO();
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            pstmnt = connection.prepareStatement(SQL_SELECT_FROM_FILM_CATEGORIES_CATEGORIES_BY_FILM_ID);
            pstmnt.setInt(1, id);
            rs = pstmnt.executeQuery();
            while (rs.next()) {
                categories.add(categoryDAO.findCategoryByID(connection, rs.getInt("CATEGORY_ID")));
            }
        } catch (Exception e) {
//            LOG.error("Can not obtain User by id.", e);
        } finally {
            DAOFactory.close(pstmnt);
            DAOFactory.close(rs);
            DAOFactory.commitAndClose(connection);
        }
        return categories;
    }

    @Override
    public void createFilmCategory(Film film, Category category, Connection connection) throws SQLException {
        PreparedStatement pstmnt = null;
        try {
            pstmnt = connection.prepareStatement(SQL_INSERT_INTO_FILM_CATEGORIES);
            int position = 1;
            pstmnt.setInt(position++, category.getId());
            pstmnt.setInt(position, film.getFilmId());
            pstmnt.execute();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not add new client.", e);
        } finally {
            DAOFactory.close(pstmnt);
        }
    }

    @Override
    public void createFilmCategory(Film film, Category category) {
        Connection connection = null;
        try {
            createFilmCategory(film,category,connection);

        } catch (Exception e){

        }finally {
            DAOFactory.commitAndClose(connection);
        }
    }

    @Override
    public void createFilmCategories(Film film, List<Category> categoryList) {
        for (Category category : categoryList){
            createFilmCategory(film, category);
        }
    }


    @Override
    public void updateFilmCategories(Film film, List<Category> categoryList) {
        Connection connection = null;
        try {
            connection = DAOFactory.getConnection();
            connection.setAutoCommit(false);
            deleteFilmCategories(film.getFilmId(),connection);
            for (Category category:categoryList){
                createFilmCategory(film,category,connection);
            }
        } catch (Exception e){
            DAOFactory.rollback(connection);
        }finally {
            DAOFactory.commitAndClose(connection);
        }
    }



    @Override
    public void deleteFilmCategories(int filmID) {
        Connection connection = null;
        try {
            deleteFilmCategories(filmID,connection);
        } catch (Exception e){

        }finally {
            DAOFactory.commitAndClose(connection);
        }

    }

    @Override
    public void deleteFilmCategories(int filmID, Connection connection) throws SQLException {
        PreparedStatement pstmnt = null;
        try {
            pstmnt = connection.prepareStatement(SQL_DELETE_FILM_CATEGORIES);
            int position = 1;
            pstmnt.setInt(position, filmID);
            pstmnt.executeUpdate();
        } catch (Exception e) {
            DAOFactory.rollback(connection);
//            LOG.error("Can not add new client.", e);
        } finally {
            DAOFactory.close(pstmnt);
        }
    }
}
