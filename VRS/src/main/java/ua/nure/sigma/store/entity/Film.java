package ua.nure.sigma.store.entity;

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
    private long generalPrice;
    private long rentPrice;
    private long bonusForRent;
    private int copiesLeft;

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

    public void setGeneralPrice(long generalPrice) {
        this.generalPrice = generalPrice;
    }

    public long getGeneralPrice() {
        return generalPrice;
    }

    public void setRentPrice(long rentPrice) {
        this.rentPrice = rentPrice;
    }

    public long getRentPrice() {
        return rentPrice;
    }

    public void setBonusForRent(long bonusForRent) {
        this.bonusForRent = bonusForRent;
    }

    public long getBonusForRent() {
        return bonusForRent;
    }

    public void setCopiesLeft(int copiesLeft) {
        this.copiesLeft = copiesLeft;
    }

    public int getCopiesLeft() {
        return copiesLeft;
    }

    @Override
    public String toString() {
        return "Film {" +
                "filmId=" + filmId +
                ", title='" + title + '\'' +
                ", year=" + year +
                ", description='" + description + '\'' +
                ", cover='" + cover + '\'' +
                ", amount=" + amount +
                ", generalPrice=" + generalPrice +
                ", rentPrice=" + rentPrice +
                ", bonusForRent=" + bonusForRent +
                ", copiesLeft=" + copiesLeft +
                '}';
    }
}
