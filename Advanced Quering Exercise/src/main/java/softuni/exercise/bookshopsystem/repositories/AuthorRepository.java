package softuni.exercise.bookshopsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.stereotype.Repository;
import softuni.exercise.bookshopsystem.dto.AuthorSummaryDto;
import softuni.exercise.bookshopsystem.entities.Author;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    List<Author> findAuthorsByFirstNameEndingWith(String suffix);

    @Query("select a.firstName, a.lastName, sum(b.copies) from Author a join a.books b group by a.id order by sum(b.copies) desc")
    List<AuthorSummaryDto> getAuthorsAndSumOfCopies();

    @Query(value = "call udp_author_books_amount(:firstName, :lastName)", nativeQuery = true)
    Integer getCountOfBooksByAuthorName(String firstName, String lastName);

}
