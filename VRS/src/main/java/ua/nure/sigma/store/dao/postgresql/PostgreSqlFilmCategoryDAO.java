package ua.nure.sigma.store.dao.postgresql;

import ua.nure.sigma.store.dao.FilmCategoryDAO;
import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public class PostgreSqlFilmCategoryDAO implements FilmCategoryDAO{
    @Override
    public List<Film> findFilmsByCategoryID(int id) {
        return null;
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
