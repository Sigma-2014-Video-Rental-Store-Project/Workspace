package ua.nure.sigma.store.logic;

import ua.nure.sigma.store.dao.DAOFactory;
import ua.nure.sigma.store.dao.FilmDAO;
import ua.nure.sigma.store.entity.Customer;
import ua.nure.sigma.store.entity.Film;
import ua.nure.sigma.store.entity.FilmForRent;

import java.util.*;

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

    private Map<Film, FilmForRent> content;
    private Customer customer;

    public Cart() {
        content = new LinkedHashMap<Film, FilmForRent>();
        customer = null;
    }

    /**
     * If the cart content contains film with specified ID or not.
     *
     * @param filmId that will be searched for.
     * @return {@code true} if contains specified film - {@code false} is does not.
     */
    public boolean contains(Integer filmId) {
        Set<Film> filmSet = content.keySet();
        for (Film film : filmSet) {
            if (film.getFilmId() == filmId) {
                return true;
            }
        }
        return false;
    }

    public Map<Film, FilmForRent> getContent() {
        return Collections.unmodifiableMap(content);
    }

    public int getCount() {
        return content.size();
    }

    public boolean addNewFilm(Integer filmId) {
        if (contains(filmId)) {
            return false;
        }
        DAOFactory daoFactory = DAOFactory.getInstance();
        FilmDAO filmDAO = daoFactory.getFilmDAO();
        Film film = filmDAO.findFilmById(filmId);
        FilmForRent filmForRent = new FilmForRent();
        filmForRent.setCopies(1);
        filmForRent.setFilmID(filmId);
        filmForRent.setDays(1);
        content.put(film, filmForRent);
        return true;
    }

    public void clear() {
        content.clear();
        customer = null;
    }

    public void setCurrentCustomer(Customer customer) {
        this.customer = customer;
    }

    public Customer getCurrentCustomer(){
        return customer;
    }

    public void remove(int filmId){
        Set<Film> filmSet = content.keySet();
        for (Film film : filmSet) {
            if (film.getFilmId() == filmId) {
                content.remove(film);
                return;
            }
        }
    }

}
