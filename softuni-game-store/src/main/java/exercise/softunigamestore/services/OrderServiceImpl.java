package exercise.softunigamestore.services;

import exercise.softunigamestore.dtos.OrderInputDto;
import exercise.softunigamestore.entities.Order;
import exercise.softunigamestore.entities.OrderItem;
import exercise.softunigamestore.entities.ShoppingCartItem;
import exercise.softunigamestore.entities.User;
import exercise.softunigamestore.repositories.OrderRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ShoppingCartService shoppingCartService;
    private final UserService userService;

    public OrderServiceImpl(OrderRepository orderRepository, ShoppingCartService shoppingCartService, UserService userService) {
        this.orderRepository = orderRepository;
        this.shoppingCartService = shoppingCartService;
        this.userService = userService;
    }

    @Override
    @Transactional
    public void create(OrderInputDto dto) {
        User user = this.userService.getRequired(dto.getUserId());

        List<ShoppingCartItem> shoppingCartItems = this.shoppingCartService.getForUser(user.getId());
        if (shoppingCartItems.isEmpty()) {throw new IllegalArgumentException("Shopping cart items not found");}

        Order order = new Order();
        order.setUser(user);
        order.setTime(LocalDateTime.now());

        Set<OrderItem> allOrderItems = new HashSet<>();
        for (ShoppingCartItem item: shoppingCartItems) {
            OrderItem current = new OrderItem();
            current.setOrder(order);
            current.setGame(item.getGame());
            current.setPrice(item.getGame().getPrice());
            current.setQuantity(item.getQuantity());

            allOrderItems.add(current);
        }

        order.setItems(allOrderItems);

        this.orderRepository.save(order);
        this.shoppingCartService.clearForUser(user.getId());
    }
}
