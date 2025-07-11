package softuni.exercise.bookshopsystem.services;

import org.springframework.stereotype.Service;
import softuni.exercise.bookshopsystem.dto.BookAuthorDto;
import softuni.exercise.bookshopsystem.dto.BookDto;
import softuni.exercise.bookshopsystem.dto.BookShortDTO;
import softuni.exercise.bookshopsystem.entities.Book;
import softuni.exercise.bookshopsystem.enums.AgeRestriction;
import softuni.exercise.bookshopsystem.enums.EditionType;
import softuni.exercise.bookshopsystem.repositories.BookRepository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

//    @Override
//    public Book createBook(BookDto bookDto, BookRelationsDto bookRelationsDto) {
//        Book book = new Book();
//        book.setTitle(bookDto.getTitle());
//        book.setCopies(bookDto.getCopies());
//        book.setPrice(bookDto.getPrice());
//        book.setReleaseDate(bookDto.getReleaseDate());
//        book.setEditionType(bookDto.getEditionType());
//        book.setAgeRestriction(bookDto.getAgeRestriction());
//
//        book.setAuthor(bookRelationsDto.getAuthor());
//        book.setCategories(Set.copyOf(bookRelationsDto.getCategories()));
//
//        return bookRepository.save(book);
//
//    }

    @Override
    public List<Book> getBookByAgeRestriction(String ageRestriction) {
        return bookRepository.getBooksByAgeRestriction(AgeRestriction.valueOf(ageRestriction.toUpperCase()));
    }

    @Override
    public List<Book> getAllGoldenBooks() {
        return bookRepository.getBooksByEditionTypeAndCopiesLessThan(EditionType.GOLD, 5000L);
    }

    @Override
    public List<Book> cheapAndExpensiveBooks() {
        return bookRepository.getBooksByPriceGreaterThanOrPriceLessThan(BigDecimal.valueOf(40), BigDecimal.valueOf(5));
    }

    @Override
    public List<BookDto> booksNotReleasedInYear(int year) {
        return bookRepository.getBooksNotReleasedInYear(year);
    }

    @Override
    public List<Book> booksReleasedBefore(LocalDate releaseDate) {
        return bookRepository.getBooksByReleaseDateBefore(releaseDate);
    }

    @Override
    public List<Book> allBooksWithString(String string) {
        return bookRepository.getBooksByTitleContains(string);
    }

    @Override
    public List<BookAuthorDto> getAllBooksAndTheirAuthors(String prefix) {
        return bookRepository.getBookAuthors(prefix);
    }

    @Override
    public Integer countOfBooksWithLongerTitle(int input) {
        return bookRepository.getCountOfBooksWithTitleLongerThan(input);
    }

    @Override
    public BookShortDTO getBookByTitle(String title) {
        return bookRepository.getBookByTitle(title);
    }

    @Override
    public Integer updateBooksCopies(Long addCopies, LocalDate date) {
        return bookRepository.increaseBookCopies(addCopies, date);
    }

    @Override
    public Integer deleteBooksByCopiesLessThan(Long copies) {
        return bookRepository.deleteBooksByCopiesLessThan(copies);
    }
}
