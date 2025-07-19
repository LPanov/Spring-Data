package exercise.productshop.dtos;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductBuyerDto {
    private String name;
    private BigDecimal price;
    private String buyerFirstName;
    private String buyerLastName;


}
