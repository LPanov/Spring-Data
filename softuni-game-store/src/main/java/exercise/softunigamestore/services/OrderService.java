package exercise.softunigamestore.services;

import exercise.softunigamestore.dtos.OrderInputDto;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

@Validated
public interface OrderService {
    void create(@Valid OrderInputDto dto);
}
