package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.enums.Dealer;

@Repository
public interface DealerRepository extends JpaRepository<Dealer, Long> {
}
