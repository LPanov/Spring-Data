package exercise.softunigamestore.services;

import exercise.softunigamestore.dtos.UserDto;
import exercise.softunigamestore.dtos.UserLoginDto;
import exercise.softunigamestore.dtos.UserRegisterDto;
import exercise.softunigamestore.entities.User;
import exercise.softunigamestore.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public UserDto ensureAdmin(UserRegisterDto userRegisterDto) {
        User admin = this.modelMapper.map(userRegisterDto, User.class);
        admin.setAdmin(true);

        try {
            this.userRepository.save(admin);
            return this.modelMapper.map(admin, UserDto.class);
        } catch (DataIntegrityViolationException e) {
            return null;
        }
    }

    @Override
    public UserDto registerUser(UserRegisterDto userRegisterDto) {
        User user = this.modelMapper.map(userRegisterDto, User.class);
        user.setAdmin(false);

        this.userRepository.save(user);
        return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto login(UserLoginDto loginDto) {
       User user = this.userRepository.findByEmailAndPassword(loginDto.getEmail(), loginDto.getPassword());
       if (user == null) {
           return null;
       }

       return this.modelMapper.map(user, UserDto.class);
    }

    @Override
    public User getRequired(Long Id) {
        return this.userRepository.findById(Id).orElseThrow();
    }
}
