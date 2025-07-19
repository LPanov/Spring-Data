package exercise.productshop.dtos.query4Dtos;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class UsersWrapperDto {
    private Integer usersCount;
    private List<UsersProductsDto> users;

    public UsersWrapperDto() {
        users = new ArrayList<>();
    }
}
