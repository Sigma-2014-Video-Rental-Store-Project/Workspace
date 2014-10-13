package ua.nure.sigma.store.entity;

import java.util.List;

/**
 * Created by vlad on 13.10.14.
 */

//Rent is entity that must be tested and discoution
public class Rent {
    //private long rentID;
    private Customer customer;;

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    private Film film;
   // private List<Film> filmList;

//    public long getRentID() {
//        return rentID;
//    }
//
//    public void setRentID(long rentID) {
//        this.rentID = rentID;
//    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

//    public List<Film> getFilmList() {
//        return filmList;
//    }
//
//    public void setFilmList(List<Film> filmList) {
//        this.filmList = filmList;
//    }
}
