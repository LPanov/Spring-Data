package softuni.exercise.bookshopsystem.dto;

import softuni.exercise.bookshopsystem.enums.AgeRestriction;
import softuni.exercise.bookshopsystem.enums.EditionType;

import java.math.BigDecimal;

public class BookShortDTO {
    private String title;
    private EditionType editionType;
    private AgeRestriction ageRestriction;
    private BigDecimal price;

    public BookShortDTO(String title, EditionType editionType, AgeRestriction ageRestriction, BigDecimal price) {
        this.title = title;
        this.editionType = editionType;
        this.ageRestriction = ageRestriction;
        this.price = price;
    }

    public String getTitle() {
        return title;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }

    public BigDecimal getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return String.format("%s %s %s %.2f", title, editionType, ageRestriction, price) ;
    }
}
