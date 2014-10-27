package ua.nure.sigma.store.entity;

import java.util.Date;

/**
 * Created by vlad on 17.10.14.
 */
public class FilmForRent {

    private int rentID;
    private int filmID;
    private int copies;
    private int copiesLeft;
    private Date futureDate;
    private Date acceptedDate;

    private transient int days;

    public int getRentID() {
        return rentID;
    }

    public void setRentID(int rentID) {
        this.rentID = rentID;
    }

    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public Date getFutureDate() {
        return futureDate;
    }

    public void setFutureDate(Date futureDate) {
        this.futureDate = futureDate;
    }

    public int getFilmID() {
        return filmID;
    }

    public void setFilmID(int filmID) {
        this.filmID = filmID;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    public int getCopiesLeft() {
        return copiesLeft;
    }

    public void setCopiesLeft(int copiesLeft) {
        this.copiesLeft = copiesLeft;
    }

    public int getDays(){
        return days;
    }

    public void setDays(int days){
        this.days = days;
    }
}
