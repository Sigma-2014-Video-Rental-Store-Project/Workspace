package ua.nure.sigma.store.dao;

import ua.nure.sigma.store.entity.Category;
import ua.nure.sigma.store.entity.Film;

import java.sql.Connection;
import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public interface FilmDAO {
    List<Film> findAllFilms();
    Film findFilmById(int id);
    public Film findFilmById(Connection connection, int id) throws Exception;
    void createFilm(Film film, Connection connection) throws Exception;
    void createFilmWithCategories(Film film, List<Category> categoryList);
    void updateFilm(Film film);
    void deleteFilm(int filmId) throws Exception;

}
