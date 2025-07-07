package exercise.usersystem.services;

import exercise.usersystem.entities.User;
import jakarta.transaction.Transactional;

import java.time.LocalDateTime;
import java.util.List;

public interface UserService {
    User create(String username, String password, String email, LocalDateTime registrationDate, LocalDateTime lastLogin);

    List<User> getUserByEmailProvider(String provider);

    List<User> getAllUsers();

    @Transactional
    void deleteAllNotLoggedBefore(LocalDateTime lastLogin);
}
