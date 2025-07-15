package exercise.softunigamestore.dtos;

import jakarta.validation.constraints.NotNull;

public class OrderInputDto {
    @NotNull
    private final Long userId;

    public OrderInputDto(Long userId) {
        this.userId = userId;
    }

    public Long getUserId() {
        return userId;
    }
}
