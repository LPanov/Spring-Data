package softuni.exercise.bookshopsystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exercise.bookshopsystem.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
