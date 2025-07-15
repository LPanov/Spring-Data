package exercise.softunigamestore.dtos;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public class UserRegisterDto {
    @Email
    private final String email;

    @NotEmpty(message = "Password cannot be empty")
    private final String password;

    @NotEmpty(message = "Full name cannot be empty")
    private final String fullName;

    public UserRegisterDto(String email, String password, String fullName) {
        this.email = email;
        this.password = password;
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getFullName() {
        return fullName;
    }
}
