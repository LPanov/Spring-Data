package exercise.softunigamestore.services;

import exercise.softunigamestore.dtos.GameInputDto;
import exercise.softunigamestore.dtos.GameDto;
import exercise.softunigamestore.entities.Game;
import jakarta.validation.Valid;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface GameService {
    GameDto create(@Valid GameInputDto dto);

    List<GameDto> getAll();

    Game getRequired(Long Id);
}
