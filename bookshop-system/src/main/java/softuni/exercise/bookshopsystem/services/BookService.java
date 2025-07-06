package softuni.exercise.bookshopsystem.services;

import softuni.exercise.bookshopsystem.dto.AuthorDto;
import softuni.exercise.bookshopsystem.dto.BookDto;
import softuni.exercise.bookshopsystem.dto.BookRelationsDto;
import softuni.exercise.bookshopsystem.entities.Book;

import java.util.List;

public interface BookService {
    Book createBook(BookDto bookDto, BookRelationsDto bookRelationsDto);

    List<Book> findBooksReleasedAfter(int year);

    List<Book> findByAuthor(String firstName, String lastName);
}
