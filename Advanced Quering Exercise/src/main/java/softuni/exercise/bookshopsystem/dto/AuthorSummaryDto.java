package softuni.exercise.bookshopsystem.dto;

public class AuthorSummaryDto {
    private final String firstName, lastName;
    private final Long copies;

    public AuthorSummaryDto(String firstName, String lastName, Long copies) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.copies = copies;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Long getCopies() {
        return copies;
    }
}
