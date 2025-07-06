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
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
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
        List<Category> categories = new ArrayList<>();
        seedCategories(categories);

        List<Author> authors = new ArrayList<>();
        seedAuthors(authors);

        seedBooks(authors, categories);

        //First Query
        List<Book> books = bookService.findBooksReleasedAfter(2000);
        for (Book book : books) {
            System.out.println(book.getTitle());
        }

        //Second Query
        List<Author> activeAuthors = authorService.findActiveBefore(1990);
        for (Author author : activeAuthors) {
            System.out.printf("%s %s\n", author.getFirstName(), author.getLastName());
        }

        //Third Query
        List<AuthorSummaryDto> productiveAuthors = authorService.getSummary();
        for (AuthorSummaryDto author : productiveAuthors) {
            System.out.printf("%s %s - %d books\n", author.getFirstName(), author.getLastName(), author.getBooksCount());
        }

        //Fourth query
        List<Book> booksByAuthor = bookService.findByAuthor("George", "Powell");
        for (Book book : booksByAuthor) {
            System.out.printf("%s (%s) - %d%n", book.getTitle(), book.getReleaseDate(), book.getCopies());
        }

    }

    private void seedAuthors(List<Author> authors) throws IOException {
        List<String> authorsData = readSeedFile("authors.txt");
        for (String line  : authorsData) {
            String[] token = line.split("\\s+");
            String firstName = token[0];
            String lastName = token[1];
            System.out.println(firstName + " " + lastName);

            AuthorDto authorDto = new AuthorDto(firstName, lastName);
            Author currentAuthor = authorService.createAuthor(authorDto);
            authors.add(currentAuthor);
        }
    }

    private void seedCategories(List<Category> categories) throws IOException {
        List<String> categoriesData = readSeedFile("categories.txt");
        for (String line  : categoriesData) {
            CategoryInputDto categoryDto = new CategoryInputDto(line);
            Category currentCategory = categoryService.createCategory(categoryDto);
            categories.add(currentCategory);
        }
    }

    private void seedBooks(List<Author> authors, List<Category> categories) throws IOException {
        List<String> booksData = readSeedFile("books.txt");
        for (String line  : booksData) {
            String[] data = line.split("\\s+");

            EditionType editionType = EditionType.values()[Integer.parseInt(data[0])];
            LocalDate releaseDate = LocalDate.parse(data[1], DateTimeFormatter.ofPattern("d/M/yyyy"));
            Long copies = Long.parseLong(data[2]);
            BigDecimal price = new BigDecimal(data[3]);
            AgeRestriction ageRestriction = AgeRestriction.values()[Integer.parseInt(data[4])];
            String title = Arrays.stream(data).skip(5).collect(Collectors.joining(" "));

            int randomAuthorIndex = ThreadLocalRandom.current().nextInt(0, authors.size());
            Author author = authors.get(randomAuthorIndex);

            int randomCategoriesCount = ThreadLocalRandom.current().nextInt(0, 3);
            List<Category> relevantCategories = new ArrayList<>();
            for (int i = 0; i < randomCategoriesCount; i++) {
                int randomCategoryIndex = ThreadLocalRandom.current().nextInt(0, categories.size());
                relevantCategories.add(categories.get(randomCategoryIndex));
            }

            BookDto bookDto = new BookDto(title, copies, price, editionType, releaseDate, ageRestriction);
            BookRelationsDto relationsDto = new BookRelationsDto(author, relevantCategories);
            Book book = bookService.createBook(bookDto, relationsDto);
        }
    }

    private List<String> readSeedFile(String fileName) throws IOException {
        ClassPathResource resource = new ClassPathResource(fileName);

        try (InputStream inputStream = resource.getInputStream()) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            return bufferedReader.lines().collect(Collectors.toList());
        }

    }
}
