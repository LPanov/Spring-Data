package softuni.exercise.bookshopsystem.dto;

public class AuthorDto {
    private final String firstName;
    private final String lastName;

    public AuthorDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
