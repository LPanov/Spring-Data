package softuni.exercise.bookshopsystem.dto;

public class BookAuthorDto {
    private String bookTitle;
    private String authorFirstName;
    private String authorLastName;

    public BookAuthorDto(String bookTitle, String authorFirstName, String authorLastName) {
        this.bookTitle = bookTitle;
        this.authorFirstName = authorFirstName;
        this.authorLastName = authorLastName;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public String getAuthorFirstName() {
        return authorFirstName;
    }

    public String getAuthorLastName() {
        return authorLastName;
    }
}
