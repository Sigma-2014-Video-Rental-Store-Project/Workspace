package ua.nure.sigma.store.logic;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmDAO;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.entity.FilmForRent;

import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Represents shopping cart, that can be filled with films to rent.
 * Provides compact API for interaction with main {@code Map} methods.
 * Must not be used in two or more threads at once.
 *
 * @author Denys Shevchenko
 * @version 1.0
 */
public class Cart {

    public static final String CART_ATTRIBUTE_NAME = "cart";

    private Map<Integer, Film> content;

    public Cart() {
        content = new LinkedHashMap<Integer, Film>();
    }

    /**
     * If the cart content contains film with specified ID or not.
     *
     * @param filmId that will be searched for.
     * @return {@code true} if contains specified film - {@code false} is does not.
     */
    public boolean contains(Integer filmId) {
        return content.containsKey(filmId);
    }

    public Map<Integer, Film> getContent(){
        return Collections.unmodifiableMap(content);
    }

    public int getCount(){
        return content.size();
    }

    public boolean addNewFilm(Integer filmId){
        if(contains(filmId)){
            return false;
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        FilmDAO filmDAO = daoFactory.getFilmDAO();
        Film film = filmDAO.findFilmById(filmId);
        content.put(filmId, film);
        return true;
    }

}
