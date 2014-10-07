package ua.nure.sigma..store.entity;

/**
 * @author Sergey Laposhko
 */
public class Film {
    private int filmId;
    private String title;
    private int year;
    private String description;
    private String cover;
    private int amount;
    private int generalPrice;
    private int rentPrice;
    private int bonusForRent;

    public void setFilmId(int filmId) {
        this.filmId = filmId;
    }

    public int getFilmId() {
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

    public void setGeneralPrice(int generalPrice) {
        this.generalPrice = generalPrice;
    }

    public int getGeneralPrice() {
        return generalPrice;
    }

    public void setRentPrice(int rentPrice) {
        this.rentPrice = rentPrice;
    }

    public int getRentPrice() {
        return rentPrice;
    }

    public void setBonusForRent(int bonusForRent) {
        this.bonusForRent = bonusForRent;
    }

    public int getBonusForRent() {
        return bonusForRent;
    }
}
