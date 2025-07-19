package exercise.productshop.serivces;


import exercise.productshop.dtos.SellerProductsDto;
import exercise.productshop.dtos.UserDto;
import exercise.productshop.dtos.UserInputDto;
import exercise.productshop.dtos.query4Dtos.UsersWrapperDto;
import exercise.productshop.entities.User;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface UserService {
    UserDto create(@Valid UserInputDto inputDto);

    User findBuyer(Long id);

    User findSeller(Long id);

    List<SellerProductsDto> usersSoldProducts();

    UsersWrapperDto getAllSellers();
}
