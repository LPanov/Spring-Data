package sofuni.exam.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import sofuni.exam.models.entity.Moon;
import sofuni.exam.models.enums.Type;

import java.util.List;

@Repository
public interface MoonRepository extends JpaRepository<Moon,Long> {
    @Query("select m from Moon m where m.planet.type = :type and m.radius between :lowBound and :highBound order by m.name")
    List<Moon> getMoonsByPlanetAndByRadius(Type type, Double lowBound, Double highBound);


}
