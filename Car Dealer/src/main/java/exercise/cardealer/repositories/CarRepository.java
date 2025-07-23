package exercise.cardealer.repositories;

import exercise.cardealer.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select c from Car c left join fetch c.parts")
    List<Car> findAllWithParts();
}
