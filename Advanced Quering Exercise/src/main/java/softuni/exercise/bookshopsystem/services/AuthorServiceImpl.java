package softuni.exercise.bookshopsystem.services;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import softuni.exercise.bookshopsystem.dto.AuthorDto;
import softuni.exercise.bookshopsystem.dto.AuthorSummaryDto;
import softuni.exercise.bookshopsystem.entities.Author;
import softuni.exercise.bookshopsystem.repositories.AuthorRepository;

import java.time.LocalDate;
import java.util.List;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author createAuthor(AuthorDto authorDto) {
        Author author = new Author();
        author.setFirstName(authorDto.getFirstName());
        author.setLastName(authorDto.getLastName());

        return authorRepository.save(author);
    }

    @Override
    public List<Author> findAuthorsByFirstNameEndingWith(String suffix) {
        return authorRepository.findAuthorsByFirstNameEndingWith(suffix);
    }

    @Override
    public List<AuthorSummaryDto> getAuthorsAndSumOfCopies() {
        return authorRepository.getAuthorsAndSumOfCopies();
    }

    @Override
    public Integer getAuthorsBookCount(String name) {
        String[] names = name.split("\\s+");
        return authorRepository.getCountOfBooksByAuthorName(names[0], names[1]);

    }
}
