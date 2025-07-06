package softuni.exercise.bookshopsystem.dto;

import softuni.exercise.bookshopsystem.enums.AgeRestriction;
import softuni.exercise.bookshopsystem.enums.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;

public class BookDto {
    private String title;
    private Long copies;
    private BigDecimal price;
    private EditionType editionType;
    private LocalDate releaseDate;
    private AgeRestriction ageRestriction;

    public BookDto(String title, Long copies, BigDecimal price, EditionType editionType, LocalDate releaseDate, AgeRestriction ageRestriction) {
        this.title = title;
        this.copies = copies;
        this.price = price;
        this.editionType = editionType;
        this.releaseDate = releaseDate;
        this.ageRestriction = ageRestriction;
    }

    public String getTitle() {
        return title;
    }

    public Long getCopies() {
        return copies;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public EditionType getEditionType() {
        return editionType;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public AgeRestriction getAgeRestriction() {
        return ageRestriction;
    }
}
