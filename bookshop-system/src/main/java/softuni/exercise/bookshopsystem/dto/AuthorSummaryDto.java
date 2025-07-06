package softuni.exercise.bookshopsystem.dto;

public class AuthorSummaryDto {
    private final String firstName, lastName;
    private final Integer booksCount;

    public AuthorSummaryDto(String firstName, String lastName, Integer booksCount) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.booksCount = booksCount;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getBooksCount() {
        return booksCount;
    }
}
