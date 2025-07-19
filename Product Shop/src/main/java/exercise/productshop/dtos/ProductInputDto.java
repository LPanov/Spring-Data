package exercise.productshop.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;


@Setter
@Getter
public class ProductInputDto {
    @Length(min = 3)
    private String name;

    @NotNull
    private BigDecimal price;

    public ProductInputDto(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
    }

    public ProductInputDto() {
    }
}
