package exercise.softunigamestore.services;

import exercise.softunigamestore.dtos.UserDto;
import exercise.softunigamestore.dtos.UserLoginDto;
import exercise.softunigamestore.dtos.UserRegisterDto;
import exercise.softunigamestore.entities.User;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UserService {
    UserDto ensureAdmin(@Valid UserRegisterDto userRegisterDto);
    UserDto registerUser(@Valid UserRegisterDto userRegisterDto);
    UserDto login(@Valid UserLoginDto loginDto);
    User getRequired(Long Id);

}
