package exercise.usersystem;

import exercise.usersystem.entities.User;
import exercise.usersystem.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class Runner implements CommandLineRunner {
    private UserService userService;

    public Runner(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void run(String... args) throws Exception {
        userService.create("user1", "Aa1&aa", "info@softuni-bulgaria.org", LocalDateTime.parse("2022-07-06T22:12:53"), LocalDateTime.now());
        userService.create("user2", "Aa1&aa", "info@gmail.com", LocalDateTime.parse("2022-07-06T22:12:53"), LocalDateTime.now());
        userService.create("user3", "Aa1&aa", "user3@gmail.com", LocalDateTime.parse("2022-07-06T22:12:53"), LocalDateTime.now());
        userService.create("user4", "Aa1&aa", "info@yahoo.co.uk", LocalDateTime.parse("2022-07-06T22:12:53"), LocalDateTime.parse("2023-07-06T22:12:53"));

        String provider = "gmail.com";

        List<User> usersWithProvider = userService.getUserByEmailProvider(provider);
        if (usersWithProvider.isEmpty()) {
            System.out.println("No users found with email domain " + provider);
        }
        else {
            for (User user : usersWithProvider) {
                System.out.printf("Username:%s Email:%s%n", user.getUsername(), user.getEmail());
            }
        }

        userService.deleteAllNotLoggedBefore(LocalDateTime.parse("2025-01-01T00:00:01.303405"));


    }
}
