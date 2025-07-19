package exercise.productshop.serivces;

import exercise.productshop.dtos.ProductDto;
import exercise.productshop.dtos.ProductInputDto;
import exercise.productshop.dtos.ProductSellerDto;
import exercise.productshop.dtos.UserDto;
import exercise.productshop.entities.Category;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Validated
public interface ProductService {
    ProductDto create(@Valid ProductInputDto inputDto, @Valid Long sellerId, @Valid Long buyerId, @Valid Set<Category> categories);

    List<ProductSellerDto> getUnsoldProductsInRange(BigDecimal lowBound, BigDecimal highBound);

    BigDecimal totalPriceById(Long id);
}
