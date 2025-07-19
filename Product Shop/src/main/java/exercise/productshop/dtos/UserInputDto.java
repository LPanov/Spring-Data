package exercise.productshop.dtos;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

@Data
public class UserInputDto {
    private String firstName;

    @Length(min = 3)
    private String lastName;

    private Integer age;

    public UserInputDto(String firstName, String lastName, Integer age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public UserInputDto() {
    }
}
