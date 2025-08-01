package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class SaleInputDto {
    @Expose
    private Boolean discounted;

    @Expose
    @NotNull
    @Length(min = 7, max = 7)
    private String number;

    @Expose
    @NotNull
    private String saleDate;

    @Expose
    private Long seller;
}