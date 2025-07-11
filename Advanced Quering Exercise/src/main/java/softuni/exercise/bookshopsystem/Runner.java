package softuni.exercise.bookshopsystem;

import jakarta.transaction.Transactional;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;
import softuni.exercise.bookshopsystem.dto.*;
import softuni.exercise.bookshopsystem.entities.Author;
import softuni.exercise.bookshopsystem.entities.Book;
import softuni.exercise.bookshopsystem.entities.Category;
import softuni.exercise.bookshopsystem.enums.AgeRestriction;
import softuni.exercise.bookshopsystem.enums.EditionType;
import softuni.exercise.bookshopsystem.services.AuthorService;
import softuni.exercise.bookshopsystem.services.BookService;
import softuni.exercise.bookshopsystem.services.CategoryService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

@Component
public class Runner implements CommandLineRunner {
    private final CategoryService categoryService;
    private final AuthorService authorService;
    private final BookService bookService;

    public Runner(CategoryService categoryService, AuthorService authorService,  BookService bookService) {
        this.categoryService = categoryService;
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @Override
    public void run(String... args) throws Exception {
        //Task 1
        //Query 1
        bookService.getBookByAgeRestriction("miNor")
                .forEach(b -> System.out.printf("%s%n", b.getTitle()));

        //Query 2
        bookService.getBookByAgeRestriction("teEN")
                .forEach(b -> System.out.printf("%s%n", b.getTitle()));

        //Task 2
        bookService.getAllGoldenBooks()
                .forEach(b -> System.out.printf("%s%n", b.getTitle()));

        //Task 3
        bookService.cheapAndExpensiveBooks()
                .forEach(b -> System.out.printf("%s - $%.2f%n", b.getTitle(), b.getPrice()));

        //Task 4
        //Query 1
        bookService.booksNotReleasedInYear(2000)
                .forEach(b -> System.out.printf("%s%n", b.getTitle()));

        //Query 2
        bookService.booksNotReleasedInYear(1998)
                .forEach(b -> System.out.printf("%s%n", b.getTitle()));

        //Task 5
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        //Query 1
        bookService.booksReleasedBefore(LocalDate.parse("12-04-1992", formatter))
                .forEach(b -> System.out.printf("%s %s %.2f%n", b.getTitle(), b.getEditionType(), b.getPrice()));

        //Query 2
        bookService.booksReleasedBefore(LocalDate.parse("30-12-1989", formatter))
                .forEach(b -> System.out.printf("%s %s %.2f%n", b.getTitle(), b.getEditionType(), b.getPrice()));

        //Task 6
        //Query 1
        authorService.findAuthorsByFirstNameEndingWith("e")
                .forEach(a -> System.out.printf("%s %s%n", a.getFirstName(), a.getLastName()));

        //Query 2
        authorService.findAuthorsByFirstNameEndingWith("dy")
                .forEach(a -> System.out.printf("%s %s%n", a.getFirstName(), a.getLastName()));

        //Task 7
        //Example 1
        bookService.allBooksWithString("sK")
                .forEach(b -> System.out.printf("%s%n", b.getTitle()));

        //Example 2
        bookService.allBooksWithString("WOR")
                .forEach(b -> System.out.printf("%s%n", b.getTitle()));

        //Task 8
        //Example 1
        bookService.getAllBooksAndTheirAuthors("Ric")
                .forEach(b -> System.out.printf("%s (%s %s)%n", b.getBookTitle(), b.getAuthorFirstName(), b.getAuthorLastName()));

        //Example 2
        bookService.getAllBooksAndTheirAuthors("gr")
                .forEach(b -> System.out.printf("%s (%s %s)%n", b.getBookTitle(), b.getAuthorFirstName(), b.getAuthorLastName()));

        //Task 9
        //Example 1
        System.out.println(bookService.countOfBooksWithLongerTitle(12));

        //Example 2
        System.out.println(bookService.countOfBooksWithLongerTitle(40));

        //Task 10
        authorService.getAuthorsAndSumOfCopies()
                .forEach(a -> System.out.printf("%s %s - %d%n", a.getFirstName(), a.getLastName(), a.getCopies()));

        //Task 11
        System.out.println(bookService.getBookByTitle("Things Fall Apart"));

        //Task 12
        //Example 1
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd MMM yyyy")
                .withLocale(Locale.ENGLISH);
        String[] input = "12 Oct 2005\n100".split("\n");

        System.out.println(Integer.parseInt(input[1]) * bookService.updateBooksCopies(Long.parseLong(input[1]), LocalDate.parse(input[0], formatDate)));

        //Example 2
        input = "06 Jun 2013\n44".split("\n");

        System.out.println(Integer.parseInt(input[1]) * bookService.updateBooksCopies(Long.parseLong(input[1]), LocalDate.parse(input[0], formatDate)));

        //Task 13
        //The first time you turn it should return 2 then 0
        System.out.println("Number of books deleted with copies less than given number\n" + bookService.deleteBooksByCopiesLessThan(220L));

        //Task 14
        //Example 1 Should return 6
        System.out.println(authorService.getAuthorsBookCount("Amanda Rice"));

        //Example 2 should return 4
        System.out.println(authorService.getAuthorsBookCount("Christina Jordan"));
    }


}
