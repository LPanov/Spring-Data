package exercise.softunigamestore.services;

import exercise.softunigamestore.dtos.ShoppingCartItemDto;
import exercise.softunigamestore.dtos.ShoppingCartItemInputDto;
import exercise.softunigamestore.entities.ShoppingCartItem;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface ShoppingCartService {
    ShoppingCartItemDto create(@Valid ShoppingCartItemInputDto shoppingCartItemInputDto);
    List<ShoppingCartItem> getForUser(@NotNull Long userId);

    void clearForUser(@NotNull Long userId);
}
