package exercise.productshop.dtos.query4Dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ProductsWrapperDto {
    private int count;
    private List<ProductInputDto> products;

    public ProductsWrapperDto() {
        products = new ArrayList<>();
    }
}
