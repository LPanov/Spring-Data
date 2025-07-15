package exercise.softunigamestore.services;

import exercise.softunigamestore.dtos.ShoppingCartItemDto;
import exercise.softunigamestore.dtos.ShoppingCartItemInputDto;
import exercise.softunigamestore.entities.Game;
import exercise.softunigamestore.entities.ShoppingCartItem;
import exercise.softunigamestore.entities.User;
import exercise.softunigamestore.repositories.ShoppingCartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
    private final ShoppingCartRepository shoppingCartRepository;
    private final ModelMapper modelMapper;
    private final GameService gameService;
    private final UserService userService;


    public ShoppingCartServiceImpl(GameService gameService, UserService userService, ShoppingCartRepository shoppingCartRepository, ModelMapper modelMapper) {
        this.gameService = gameService;
        this.userService = userService;
        this.shoppingCartRepository = shoppingCartRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ShoppingCartItemDto create(ShoppingCartItemInputDto dto) {
        User user = this.userService.getRequired(dto.getUserId());
        Game game = this.gameService.getRequired(dto.getGameId());

        ShoppingCartItem item = new ShoppingCartItem();
        item.setGame(game);
        item.setUser(user);
        item.setQuantity(dto.getQuantity());

        this.shoppingCartRepository.save(item);
        return this.modelMapper.map(item, ShoppingCartItemDto.class);
    }

    @Override
    public List<ShoppingCartItem> getForUser(Long userId) {
        return this.shoppingCartRepository.findAllByUserId(userId);
    }

    @Override
    public void clearForUser(Long userId) {
        this.shoppingCartRepository.removeAllByUserId(userId);
    }
}
