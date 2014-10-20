package ua.nure.sigma.store.dao;

import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public interface FilmCategoryDAO {
    List<Film> findFilmsByCategoryID(int id);
    List<Category> findCategoriesByFilmID(int id);
    void createFilmCategory(Film film, Category category,Connection connection)throws SQLException;
    void updateFilmCategories(Film film, List<Category> categoryList);
    void createFilmCategory(Film film, Category category);
    void createFilmCategories(Film film, List<Category> categoryList);
    void deleteFilmCategories(int filmID);
    void deleteFilmCategories(int filmID,Connection connection)throws SQLException;
}
