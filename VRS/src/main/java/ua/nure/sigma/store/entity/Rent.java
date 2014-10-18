package ua.nure.sigma.store.entity;

import java.util.Date;
import java.util.List;

/**
 * Created by vlad on 13.10.14.
 */

public class Rent {
    private long rentID;
    private int customerID;
    private Date rentDate;
    private List<FilmForRent> filmList;

    public Date getRentDate() {
        return rentDate;
    }

    public void setRentDate(Date rentDate) {
        this.rentDate = rentDate;
    }

    public long getRentID() {
        return rentID;
    }

    public void setRentID(long rentID) {
        this.rentID = rentID;
    }

    public List<FilmForRent> getFilmList() {
        return filmList;
    }

    public void setFilmList(List<FilmForRent> filmList) {
        this.filmList = filmList;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }
}

