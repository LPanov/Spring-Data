package exercise.productshop.serivces;

import exercise.productshop.dtos.ProductDto;
import exercise.productshop.dtos.ProductInputDto;
import exercise.productshop.dtos.ProductSellerDto;
import exercise.productshop.dtos.UserDto;
import exercise.productshop.entities.Category;
import exercise.productshop.entities.Product;
import exercise.productshop.entities.User;
import exercise.productshop.repositories.ProductRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ProductServiceImpl implements ProductService {

    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;
    private final UserService userService;

    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository, UserService userService) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
        this.userService = userService;
    }

    @Override
    public ProductDto create(ProductInputDto inputDto, Long sellerId, Long buyerId, Set<Category> categories) {
        Product product = modelMapper.map(inputDto, Product.class);
        product.setSeller(userService.findSeller(sellerId));
        product.setBuyer(userService.findBuyer(buyerId));

        product.setCategories(categories);

        productRepository.save(product);

        ProductDto outputDto = modelMapper.map(product, ProductDto.class);
        if (product.getBuyer()!= null) {
            outputDto.setBuyerId(product.getBuyer().getId());
        }
        else outputDto.setBuyerId(null);

        outputDto.setSellerId(sellerId);

        return outputDto;
    }

    @Override
    public List<ProductSellerDto> getUnsoldProductsInRange(BigDecimal lowBound, BigDecimal highBound) {
        List<ProductSellerDto> products = new ArrayList<>();
        for (Product product : productRepository.getProductsByPriceWithNoBuyer(lowBound, highBound)) {
            ProductSellerDto productSeller = new ProductSellerDto();
            productSeller.setName(product.getName());
            productSeller.setPrice(product.getPrice());
            productSeller.setSellerName(product.getSeller().getFirstName() + " " + product.getSeller().getLastName());

            products.add(productSeller);
        }

        return products;
    }

    @Override
    public BigDecimal totalPriceById(Long id) {
        return productRepository.getTotalPriceById(id);
    }
}
