package exercise.usersystem.services;

import exercise.usersystem.entities.User;
import exercise.usersystem.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User create(String username, String password, String email, LocalDateTime registrationDate, LocalDateTime lastLogin) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setRegisteredOn(registrationDate);
        user.setLastLogin(lastLogin);

        return userRepository.save(user);
    }

    @Override
    public List<User> getUserByEmailProvider(String provider) {
        return userRepository.getUserByEmailContaining(provider);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteAllNotLoggedBefore(LocalDateTime lastLogin) {
        int count = 0;
        for (User user : userRepository.findAll()) {
            if (user.getLastLogin().isBefore(lastLogin)) {
                user.setDeleted(true);
                count++;
            }
        }

        System.out.println("Count of deleted user: " + count);

        userRepository.deleteAllByIsDeletedTrue();

    }


}
