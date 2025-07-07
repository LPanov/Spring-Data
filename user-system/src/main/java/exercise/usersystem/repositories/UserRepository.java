package exercise.usersystem.repositories;

import exercise.usersystem.entities.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    List<User> getUserByEmailContaining(String provider);

    void deleteAllByIsDeletedTrue();
}
