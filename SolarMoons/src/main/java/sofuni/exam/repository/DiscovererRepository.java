package sofuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import sofuni.exam.models.entity.Discoverer;

@Repository
public interface DiscovererRepository extends JpaRepository<Discoverer, Long> {
}
