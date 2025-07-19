package exercise.productshop;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import exercise.productshop.dtos.*;
import exercise.productshop.dtos.query4Dtos.UsersWrapperDto;
import exercise.productshop.entities.Category;
import exercise.productshop.serivces.CategoryService;
import exercise.productshop.serivces.ProductService;
import exercise.productshop.serivces.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

@Component
public class Runner implements CommandLineRunner {
    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;
    private final ObjectMapper objectMapper;


    public Runner(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
        this.objectMapper = new ObjectMapper();
    }

    @Override
    public void run(String... args) throws Exception {
        //List<UserDto> users = seedUsers();
        //seedCategories();
        //List<ProductDto> products = seedProducts(users);

        //Query 1
//        List<ProductSellerDto> unsoldProducts = productService.getUnsoldProductsInRange(BigDecimal.valueOf(500), BigDecimal.valueOf(1000));
//        String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(unsoldProducts);
//        System.out.println(json);

        //Query 2
//        List<SellerProductsDto> sellerWithSoldProducts = userService.usersSoldProducts();
//        String json2 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(sellerWithSoldProducts);
//        System.out.println(json2);

        //Query 3
//        List<CategoryProductsCountDto> categoriesReport = categoryService.categoriesReports();
//        String json3 = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(categoriesReport);
//        System.out.println(json3);

        //Query 4
        UsersWrapperDto usersReport = userService.getAllSellers();
        String filePath = "users_report.json";

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(filePath), usersReport);
            System.out.println("JSON data successfully exported to: " + filePath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private List<CategoryDto> seedCategories() throws IOException {
        File path = new File("src/main/resources/jsons/categories.json");
        List<CategoryInputDto> categoryInputs = objectMapper.readValue(path, new TypeReference<List<CategoryInputDto>>() {});

        List<CategoryDto> categoryOutput = new ArrayList<>();
        for (CategoryInputDto categoryInput : categoryInputs) {
            CategoryDto categoryDto = categoryService.create(categoryInput);

            categoryOutput.add(categoryDto);
        }

        return categoryOutput;
    }

    private List<ProductDto> seedProducts(List<UserDto> users) throws IOException {
        File path = new File("src/main/resources/jsons/products.json");
        List<ProductInputDto> productInputs = objectMapper.readValue(path, new TypeReference<List<ProductInputDto>>() {});

        List<ProductDto> productsOutput = new ArrayList<>();
        for (ProductInputDto productInput : productInputs) {
            long randomSellerIndex = ThreadLocalRandom.current().nextLong(1, users.size());
            long randomBuyerIndex = ThreadLocalRandom.current().nextLong((long) Math.ceil(users.size()*1.1));

            int randomListSize = ThreadLocalRandom.current().nextInt(categoryService.findAll().size());
            Set<Category> categories = new HashSet<>();
            List<Long> randomIds = new ArrayList<>();
            for (int i = 0; i < randomListSize; i++) {
                long randomId = ThreadLocalRandom.current().nextLong(1, categoryService.findAll().size());

                if (!randomIds.contains(randomId)) {
                    categories.add(categoryService.findById(randomId));
                }

                randomIds.add(randomId);
            }

            ProductDto outputDto = productService.create(productInput, randomSellerIndex, randomBuyerIndex, categories);

            productsOutput.add(outputDto);
        }

        return productsOutput;
    }

    private List<UserDto> seedUsers() throws IOException {
        File path = new File("src/main/resources/jsons/users.json");
        List<UserInputDto> usersInputs = objectMapper.readValue(path, new TypeReference<List<UserInputDto>>() {});

        List<UserDto> result = new ArrayList<>();
        for (UserInputDto user : usersInputs) {
            UserDto userDto = userService.create(user);

            result.add(userDto);
        }

        return result;
    }

    private InputStream readResourcedFileAsStream(String path) throws IOException, IOException {
        ClassPathResource resource = new ClassPathResource(path);
        return resource.getInputStream();
    }
}
