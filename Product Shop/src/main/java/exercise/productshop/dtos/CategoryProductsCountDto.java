package exercise.productshop.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@JsonPropertyOrder({
        "category",
        "productsCount",
        "averagePrice",
        "totalRevenue"
})
public class CategoryProductsCountDto {
    @JsonProperty("category")
    private String name;
    private Integer productsCount;
    @JsonProperty("averagePrice")
    private BigDecimal avgPrice;
    private BigDecimal totalRevenue;
}
