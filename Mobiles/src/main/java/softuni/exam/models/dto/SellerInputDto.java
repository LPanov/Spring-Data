package softuni.exam.models.dto;

import com.google.gson.annotations.Expose;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class SellerInputDto {
    @Expose
    @NotNull
    @Length(min=2, max=30)
    private String firstName;

    @Expose
    @NotNull
    @Length(min=2, max=30)
    private String lastName;

    @Expose
    @NotNull
    @Length(min=3, max=6)
    private String personalNumber;
}
