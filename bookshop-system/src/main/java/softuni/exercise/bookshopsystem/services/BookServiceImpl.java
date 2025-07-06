package softuni.exercise.bookshopsystem.services;

import org.springframework.stereotype.Service;
import softuni.exercise.bookshopsystem.dto.BookDto;
import softuni.exercise.bookshopsystem.dto.BookRelationsDto;
import softuni.exercise.bookshopsystem.entities.Book;
import softuni.exercise.bookshopsystem.repositories.BookRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
public class BookServiceImpl implements BookService {
    private BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book createBook(BookDto bookDto, BookRelationsDto bookRelationsDto) {
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setCopies(bookDto.getCopies());
        book.setPrice(bookDto.getPrice());
        book.setReleaseDate(bookDto.getReleaseDate());
        book.setEditionType(bookDto.getEditionType());
        book.setAgeRestriction(bookDto.getAgeRestriction());

        book.setAuthor(bookRelationsDto.getAuthor());
        book.setCategories(Set.copyOf(bookRelationsDto.getCategories()));

        return bookRepository.save(book);

    }

    @Override
    public List<Book> findBooksReleasedAfter(int year) {
        LocalDate date = LocalDate.of(year, 1, 1);
        return bookRepository.findAllByReleaseDateGreaterThanEqual(date);
    }

    @Override
    public List<Book> findByAuthor(String firstName, String lastName) {
        return bookRepository.findAllByAuthorFirstNameAndAuthorLastNameOrderByReleaseDateDescTitleAsc(firstName, lastName);
    }
}
