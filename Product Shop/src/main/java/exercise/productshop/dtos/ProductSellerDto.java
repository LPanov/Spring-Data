package exercise.productshop.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class ProductSellerDto {
    private String name;
    private BigDecimal price;
    @JsonProperty("seller")
    private String sellerName;

}
