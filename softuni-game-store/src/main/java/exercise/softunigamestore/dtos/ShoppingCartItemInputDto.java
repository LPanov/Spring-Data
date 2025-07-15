package exercise.softunigamestore.dtos;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public class ShoppingCartItemInputDto {
    @NotNull
    private final Long gameId;

    @NotNull
    private final Long userId;

    @NotNull
    @Positive
    private final Integer quantity;

    public ShoppingCartItemInputDto( Long userId, Long gameId, Integer quantity) {
        this.gameId = gameId;
        this.userId = userId;
        this.quantity = quantity;
    }

    public Long getGameId() {
        return gameId;
    }

    public Long getUserId() {
        return userId;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
