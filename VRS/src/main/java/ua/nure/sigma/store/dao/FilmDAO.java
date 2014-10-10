package ua.nure.sigma.store.dao;

import ua.nure.sigma.store.entity.Film;

import java.util.List;

/**
 * Created by nikolaienko on 07.10.14.
 */
public interface FilmDAO {
    List<Film> findAllFilms();
    Film findFilmByID(int id);
    void createFilm(Film film);
    void updateFilm(Film film);
    void deleteFilm(Film film);
}
