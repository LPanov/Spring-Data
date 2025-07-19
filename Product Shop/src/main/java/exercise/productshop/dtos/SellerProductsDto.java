package exercise.productshop.dtos;

import exercise.productshop.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SellerProductsDto {
    private String firstName;
    private String lastName;
    private List<ProductBuyerDto> soldProducts;
}
