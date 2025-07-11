package softuni.exercise.bookshopsystem.repositories;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import softuni.exercise.bookshopsystem.dto.BookAuthorDto;
import softuni.exercise.bookshopsystem.dto.BookDto;
import softuni.exercise.bookshopsystem.dto.BookShortDTO;
import softuni.exercise.bookshopsystem.entities.Book;
import softuni.exercise.bookshopsystem.enums.AgeRestriction;
import softuni.exercise.bookshopsystem.enums.EditionType;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    List<Book> getBooksByAgeRestriction(AgeRestriction ageRestriction);

    List<Book> getBooksByEditionTypeAndCopiesLessThan(EditionType editionType, Long copies);

    List<Book> getBooksByPriceGreaterThanOrPriceLessThan(BigDecimal lowBound, BigDecimal highBound);

    @Query("select b.title from Book b where year(b.releaseDate) <> :year")
    List<BookDto> getBooksNotReleasedInYear(int year);

    List<Book> getBooksByReleaseDateBefore(LocalDate releaseDate);

    List<Book> getBooksByTitleContains(String title);

    @Query("select b.title, b.author.firstName, b.author.lastName from Book b join b.author a where a.lastName like :prefix%")
    List<BookAuthorDto> getBookAuthors(String prefix);

    @Query("select count(b) from Book b where length(b.title) > :input ")
    Integer getCountOfBooksWithTitleLongerThan(int input);

    @Query("select b.title, b.editionType, b.ageRestriction, b.price from Book b where b.title = :title")
    BookShortDTO getBookByTitle(String title);

    @Modifying
    @Transactional
    @Query("update Book b set b.copies = b.copies + :addCopies where b.releaseDate > :date")
    Integer increaseBookCopies(Long addCopies, LocalDate date);

    @Transactional
    Integer deleteBooksByCopiesLessThan(Long copies);
}
