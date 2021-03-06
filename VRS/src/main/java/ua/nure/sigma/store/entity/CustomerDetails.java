package ua.nure.sigma.store.entity;

import org.apache.log4j.Logger;

import java.util.Date;

/**
 * Created by Maksim Sinkevich on 28.10.2014.
 */
public class CustomerDetails {
    private static final Logger LOG = Logger.getLogger(CustomerDetails.class);
    private static final long MILLISECONDS_IN_DAY = 86400000;

    private Film film;
    private FilmForRent filmForRent;
    private Rent rent;

    public Rent getRent() {
        return rent;
    }

    public FilmForRent getFilmForRent() {
        return filmForRent;
    }

    public Film getFilm() {
        return film;
    }

    public void setRent(Rent rent) {
        this.rent = rent;
    }

    public void setFilmForRent(FilmForRent filmForRent) {
        this.filmForRent = filmForRent;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public String getName() {
        return film.getTitle();
    }

    public void setName(String name) {
        film.setTitle(name);
    }

    public Date getAcceptedDate() {
        return filmForRent.getAcceptedDate();
    }

    public void setAcceptedDate(Date endDate) {
        filmForRent.setAcceptedDate(endDate);
    }

    public Date getFutureDate(){
        return filmForRent.getFutureDate();
    }

    public Date getStartDate() {
        return rent.getRentDate();
    }

    public void setStartDate(Date startDate) {
        rent.setRentDate(startDate);
    }

    public int getDaysLeft() {
        int days = (int) ((filmForRent.getFutureDate().getTime() - System.currentTimeMillis()) / MILLISECONDS_IN_DAY + 1);
        return days > 0 ? days : 0;
    }

    public int getCopiesLeft() {
        return filmForRent.getCopiesLeft();
    }
}
