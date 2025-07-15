package exercise.softunigamestore.services;

import exercise.softunigamestore.dtos.GameInputDto;
import exercise.softunigamestore.dtos.GameDto;
import exercise.softunigamestore.entities.Game;
import exercise.softunigamestore.repositories.GameRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GameServiceImpl implements GameService {
    private final GameRepository gameRepository;
    private final ModelMapper modelMapper;


    public GameServiceImpl(GameRepository gameRepository, ModelMapper modelMapper) {
        this.gameRepository = gameRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public GameDto create(GameInputDto dto) {
        Game game = this.modelMapper.map(dto, Game.class);

        game.setReleaseDate(LocalDate.now());

        this.gameRepository.save(game);

        return this.modelMapper.map(game, GameDto.class);
    }

    @Override
    public List<GameDto> getAll() {
        List<Game> games = this.gameRepository.findAll();

        return games.stream().map(g -> this.modelMapper.map(g, GameDto.class)).collect(Collectors.toList());
    }

    @Override
    public Game getRequired(Long Id) {
        return this.gameRepository.findById(Id).orElseThrow();
    }
}
