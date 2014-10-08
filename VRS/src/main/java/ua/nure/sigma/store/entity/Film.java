package ua.nure.sigma.store.entity;

/**
 * @author Sergey Laposhko
 */
public class Film {
    private long filmId;
    private String title;
    private int year;
    private String description;
    private String cover;
    private int amount;
    private double generalPrice;
    private double rentPrice;
    private double bonusForRent;

    public void setFilmId(long filmId) {
        this.filmId = filmId;
    }

    public long getFilmId() {
        return filmId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getYear() {
        return year;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getCover() {
        return cover;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getAmount() {
        return amount;
    }

    public void setGeneralPrice(double generalPrice) {
        this.generalPrice = generalPrice;
    }

    public double getGeneralPrice() {
        return generalPrice;
    }

    public void setRentPrice(double rentPrice) {
        this.rentPrice = rentPrice;
    }

    public double getRentPrice() {
        return rentPrice;
    }

    public void setBonusForRent(double bonusForRent) {
        this.bonusForRent = bonusForRent;
    }

    public double getBonusForRent() {
        return bonusForRent;
    }
}
