package softuni.exercise.bookshopsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exercise.bookshopsystem.dto.AuthorSummaryDto;
import softuni.exercise.bookshopsystem.entities.Author;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findDistinctByBooksReleaseDateLessThan(LocalDate releaseDate);

    @Query("select a.firstName, a.lastName, size(a.books) from Author as a order by size(a.books) desc")
    List<AuthorSummaryDto> getSummary();
}
