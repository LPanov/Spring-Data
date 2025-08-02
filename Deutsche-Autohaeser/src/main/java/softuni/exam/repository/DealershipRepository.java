package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.enums.Dealership;

@Repository
public interface DealershipRepository extends JpaRepository<Dealership, Long> {
}
