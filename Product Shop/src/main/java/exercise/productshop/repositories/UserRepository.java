package exercise.productshop.repositories;

import exercise.productshop.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id);

    User findUserById(Long id);

    @Query("select u from User u where size(u.soldProducts) > 0 order by size(u.soldProducts) desc, u.lastName")
    List<User> findAllSellers();
}
