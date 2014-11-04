package ua.nure.sigma.store.dao;

import ua.nure.sigma.store.entity.FilmForRent;

import java.sql.Connection;
import java.util.List;

/**
 * Created by vlad on 16.10.14.
 */
public interface FilmRentedDAO {

    List<FilmForRent> findFilmRentedByRentID(long rentID);
    void createFilmsRented(long rentID, List<FilmForRent> forRents, Connection connection) throws Exception;
    int findFilmCopiesAtRent(int filmID);
    int findFilmRentLeftCopies(long rentID, int filmID);
    void updateFilmRent(long rentID, int filmID, int copies) throws Exception;
    void closeFilmRent(long rentID, int filmID);
    void updateFilmRent(long rentID, int filmID, int copies, int copiesLeft,Connection connection);
    public void closeFilmRent(long rentID);

}
