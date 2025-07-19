package exercise.productshop.dtos.query4Dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class UsersProductsDto {
    private String firstName;
    private String lastName;
    private Integer age;
    private ProductsWrapperDto soldProducts;
}
