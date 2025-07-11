package softuni.exercise.bookshopsystem.services;

import softuni.exercise.bookshopsystem.dto.BookAuthorDto;
import softuni.exercise.bookshopsystem.dto.BookDto;
import softuni.exercise.bookshopsystem.dto.BookShortDTO;
import softuni.exercise.bookshopsystem.entities.Book;

import java.time.LocalDate;
import java.util.List;

public interface BookService {
    //Book createBook(BookDto bookDto, BookRelationsDto bookRelationsDto);

    List<Book> getBookByAgeRestriction(String ageRestriction);

    List<Book> getAllGoldenBooks();

    List<Book> cheapAndExpensiveBooks();

    List<BookDto> booksNotReleasedInYear(int year);

    List<Book> booksReleasedBefore(LocalDate releaseDate);

    List<Book> allBooksWithString(String string);

    List<BookAuthorDto> getAllBooksAndTheirAuthors(String prefix);

    Integer countOfBooksWithLongerTitle(int input);

    BookShortDTO getBookByTitle(String title);

    Integer updateBooksCopies(Long addCopies, LocalDate date);

    Integer deleteBooksByCopiesLessThan(Long copies);

}
