package softuni.exercise.bookshopsystem.dto;

import softuni.exercise.bookshopsystem.entities.Author;
import softuni.exercise.bookshopsystem.entities.Category;

import java.util.List;

public class BookRelationsDto {
    private Author author;
    private List<Category> categories;

    public BookRelationsDto(Author author, List<Category> categories) {
        this.author = author;
        this.categories = categories;
    }

    public Author getAuthor() {
        return author;
    }

    public List<Category> getCategories() {
        return categories;
    }
}
