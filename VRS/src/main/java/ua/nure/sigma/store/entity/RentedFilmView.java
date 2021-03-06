package ua.nure.sigma.store.entity;

import java.util.Date;

/**
 * Created by vlad on 04.11.14.
 */
public class RentedFilmView {
    private String title;
    private int count;
    private Date acceptedDate;
    private long price;

    public RentedFilmView(String title, int count, Date acceptedDate, long price) {
        this.title = title;
        this.count = count;
        this.acceptedDate = acceptedDate;
        this.price = price;
    }

    public String getTitle() {

        return title;
    }

    public int getCount() {
        return count;
    }

    public Date getAcceptedDate() {
        return acceptedDate;
    }

    public long getPrice() {
        return price;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setAcceptedDate(Date acceptedDate) {
        this.acceptedDate = acceptedDate;
    }

    public void setPrice(long price) {
        this.price = price;
    }
}
