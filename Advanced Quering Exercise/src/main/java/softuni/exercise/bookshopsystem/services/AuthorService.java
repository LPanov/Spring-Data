package softuni.exercise.bookshopsystem.services;

import softuni.exercise.bookshopsystem.dto.AuthorDto;
import softuni.exercise.bookshopsystem.dto.AuthorSummaryDto;
import softuni.exercise.bookshopsystem.entities.Author;

import java.time.LocalDate;
import java.util.List;

public interface AuthorService {
    Author createAuthor(AuthorDto authorDto);

    List<Author> findAuthorsByFirstNameEndingWith(String suffix);

    List<AuthorSummaryDto> getAuthorsAndSumOfCopies();

    Integer getAuthorsBookCount(String name);
}
