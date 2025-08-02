package softuni.exam.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import softuni.exam.models.entity.CarType;
import softuni.exam.models.entity.enums.Car;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    @Query("select c from Car c where c.carType = :type and c.mileage < :mileage order by c.mileage desc")
    List<Car> getCarsByTypeAndMileage(CarType type, int mileage);
}
