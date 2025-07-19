package exercise.productshop.serivces;

import exercise.productshop.dtos.*;
import exercise.productshop.dtos.query4Dtos.*;
import exercise.productshop.dtos.query4Dtos.ProductInputDto;
import exercise.productshop.entities.Product;
import exercise.productshop.entities.User;
import exercise.productshop.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;
    private final UserRepository userRepository;

    public UserServiceImpl(ModelMapper modelMapper, UserRepository userRepository) {
        this.modelMapper = modelMapper;
        this.userRepository = userRepository;
    }

    @Override
    public UserDto create(UserInputDto inputDto) {
        User user = modelMapper.map(inputDto, User.class);
        userRepository.save(user);

        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User findBuyer(Long id) {
        Optional<User> userOptional = userRepository.findById(id);

        return userOptional.orElse(null);
    }

    @Override
    public User findSeller(Long id) {
        return userRepository.findUserById(id);
    }

    @Override
    public List<SellerProductsDto> usersSoldProducts() {
        List<User> users = userRepository.findAll();
        List<SellerProductsDto> usersWithSoldProducts = new ArrayList<>();

        List<ProductBuyerDto> soldProducts = new ArrayList<>();
        for (User  user : users) {
            boolean hasSoldProduct = true;
            for (Product sold :  user.getSoldProducts()) {
                if (sold.getBuyer() != null) {
                    ProductBuyerDto soldProduct = new ProductBuyerDto();
                    soldProduct.setName(sold.getName());
                    soldProduct.setPrice(sold.getPrice());
                    soldProduct.setBuyerFirstName(sold.getBuyer().getFirstName());
                    soldProduct.setBuyerLastName(sold.getBuyer().getLastName());

                    soldProducts.add(soldProduct);
                }
                else {
                    hasSoldProduct = false;
                }
            }

            if (hasSoldProduct) {
                SellerProductsDto sellerProductsDto = new SellerProductsDto();
                sellerProductsDto.setFirstName(user.getFirstName());
                sellerProductsDto.setLastName(user.getLastName());
                sellerProductsDto.setSoldProducts(soldProducts);

                usersWithSoldProducts.add(sellerProductsDto);
            }
        }

        return usersWithSoldProducts;
    }

    @Override
    public UsersWrapperDto getAllSellers() {
        UsersWrapperDto result = new UsersWrapperDto();
        List<User> sellers = userRepository.findAllSellers();

        result.setUsersCount(sellers.size());

        for (User seller : sellers) {
            UsersProductsDto usersProductsDto = new UsersProductsDto();

            ProductsWrapperDto productsWrapperDto = new ProductsWrapperDto();
            productsWrapperDto.setCount(seller.getSoldProducts().size());

            for (Product product : seller.getSoldProducts()) {
                ProductInputDto productInput = modelMapper.map(product, ProductInputDto.class);
                productsWrapperDto.getProducts().add(productInput);
            }

            usersProductsDto.setFirstName(seller.getFirstName());
            usersProductsDto.setLastName(seller.getLastName());
            usersProductsDto.setAge(seller.getAge());
            usersProductsDto.setSoldProducts(productsWrapperDto);

            result.getUsers().add(usersProductsDto);
        }


        return result;
    }
}
