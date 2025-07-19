package exercise.productshop.dtos.query4Dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;


@Setter
@Getter
public class ProductInputDto {
    private String name;
    private BigDecimal price;

    public ProductInputDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public ProductInputDto() {
    }
}
