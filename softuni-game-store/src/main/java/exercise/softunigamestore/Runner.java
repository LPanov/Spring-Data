package exercise.softunigamestore;

import exercise.softunigamestore.dtos.*;
import exercise.softunigamestore.services.GameService;
import exercise.softunigamestore.services.OrderService;
import exercise.softunigamestore.services.ShoppingCartService;
import exercise.softunigamestore.services.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Function;

@Component
public class Runner implements CommandLineRunner {
    private final GameService gameService;
    private final UserService userService;
    private final ShoppingCartService shoppingCartService;
    private final OrderService orderService;

    private UserDto currentUser;

    public Runner(GameService gameService, UserService userService, ShoppingCartService shoppingCartService, OrderService orderService) {
        this.gameService = gameService;
        this.userService = userService;
        this.shoppingCartService = shoppingCartService;
        this.orderService = orderService;
    }

    @Override
    public void run(String... args) throws Exception {
        this.ensureFirstAdmin();

        Scanner scanner = new Scanner(System.in);

        Map<String, Function<String[], String>> commands = new HashMap<>();
        commands.put("Register", this::register);
        commands.put("Login", this::login);
        commands.put("WhoAmI", this::whoAmI);
        commands.put("Logout", this::logout);
        commands.put("AddGame", this::addGame);
        commands.put("AllGames", this::listAllGames);
        commands.put("BuyGame", this::buyGame);
        commands.put("Purchase", this::purchase);

        String input;
        while (!(input = scanner.nextLine()).equals("Exit")) {
            String[] data = input.split("\\|");

            String command = data[0];
            Function<String[], String> action = commands.get(command);

            String output = execute(action, data);
            System.out.println(output);
        }
    }

    private String purchase(String[] data) {
        this.ensureAuthenticated();
        OrderInputDto dto = new OrderInputDto(this.currentUser.getId());
        this.orderService.create(dto);

        return "Order was created successfully";
    }

    private String buyGame(String[] data) {
        this.ensureAuthenticated();

        long gameId = Long.parseLong(data[1]);
        int quantity = Integer.parseInt(data[2]);
        ShoppingCartItemInputDto input = new ShoppingCartItemInputDto(this.currentUser.getId(), gameId, quantity);
        ShoppingCartItemDto createdItem = this.shoppingCartService.create(input);

        return String.format("Game \"%s\" was added to your shopping cart successfully. Shopping cart item ID %d",createdItem.getGame().getTitle(), createdItem.getId());
    }

    private String listAllGames(String[] data) {
        this.ensureAuthenticated();

        StringBuilder sb = new StringBuilder();
        sb.append("All games:");

        List<GameDto> games = this.gameService.getAll();
        for (GameDto game : games) {
            sb.append(String.format("%nGame \"%s\" with ID %d and price %.2f", game.getTitle(), game.getId(), game.getPrice()));
        }

        return sb.toString();
    }

    private String addGame(String[] data) {
        this.ensureAdmin();

        GameInputDto dto = new GameInputDto(data[1], data[2], data[3], new BigDecimal(data[4]), data[5]);
        GameDto createGame = this.gameService.create(dto);

        return String.format("Game \"%s\" with ID: %d created successfully",createGame.getTitle(), createGame.getId());

    }

    private String whoAmI(String[] data) {
        this.ensureAuthenticated();

        return String.format("You are logged in as \"%s\" with ID", this.currentUser.getEmail(), this.currentUser.getId());
    }

    private static String execute(Function<String[], String> action, String[] data) {
        String output;
        if (action == null) {
            return "Unrecognized command!";
        }

        try {
            return action.apply(data);
        } catch (Exception e) {
            return String.format("ERROR! %s%n", e.getMessage());
        }
    }

    private String register(String[] data) {
        this.ensureFirstAdmin();

        UserRegisterDto dto = new UserRegisterDto(data[1], data[2], data[3]);
        UserDto userDto = this.userService.registerUser(dto);

        return String.format("User \"%s\" with ID %d was registered successfully!",  dto.getFullName(), userDto.getId());
    }

    private String login(String[] data) {
        this.ensureFirstAdmin();

        UserLoginDto dto = new UserLoginDto(data[1], data[2]);
        UserDto loggedInUser = this.userService.login(dto);

        if (loggedInUser == null) return "Invalid credentials!";

        this.currentUser = loggedInUser;
        return String.format("User \"%s\" was logged in successfully!",  dto.getEmail());
    }

    private String logout(String[] data) {
        this.ensureAuthenticated();

        this.currentUser = null;
        return "You logged out successfully!";
    }

    private void ensureAuthenticated() {
        if (this.currentUser == null) {
            throw new IllegalStateException("You are not logged in! Please log in before executing this command!");
        }
    }

    private void ensureAdmin() {
        this.ensureAuthenticated();
        if (!currentUser.getAdmin()) {
            throw new IllegalStateException("You are not admin. Please, contact the system administrator");
        }
    }

    private void ensureAnonymous() {
        if (this.currentUser != null) {
            throw new IllegalStateException("You are already logged in!");
        }
    }

    private void ensureFirstAdmin() {
        UserRegisterDto userRegisterDto = new UserRegisterDto("game-store@admin.com", "123456", "John Doe");

        UserDto firstAdmin = this.userService.ensureAdmin(userRegisterDto);

        if (firstAdmin != null) {
            System.out.printf("The first admin user was created successfully with ID %d%n", firstAdmin.getId());
        }
        else {
            System.out.println("The first admin user is already created");
        }

    }
}
