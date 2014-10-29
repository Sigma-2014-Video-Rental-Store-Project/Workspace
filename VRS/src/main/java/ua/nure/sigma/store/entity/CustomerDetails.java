package ua.nure.sigma.store.entity;

import java.util.Date;

/**
 * Created by Maksim Sinkevich on 28.10.2014.
 */
public class CustomerDetails {
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

    public Date getEndDate() {
        return filmForRent.getAcceptedDate();
    }

    public void setEndDate(Date endDate) {
        filmForRent.setAcceptedDate(endDate);
    }

    public Date getStartDate() {
        return rent.getRentDate();
    }

    public void setStartDate(Date startDate) {
        rent.setRentDate(startDate);
    }

    public int getDaysLeft() {
        return (int) ((filmForRent.getFutureDate().getTime() - rent.getRentDate().getTime()) / MILLISECONDS_IN_DAY);
    }
}
