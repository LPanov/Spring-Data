package exercise.productshop.serivces;

import exercise.productshop.dtos.CategoryDto;
import exercise.productshop.dtos.CategoryInputDto;
import exercise.productshop.dtos.CategoryProductsCountDto;
import exercise.productshop.entities.Category;
import exercise.productshop.repositories.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    private final ModelMapper modelMapper;
    private final CategoryRepository categoryRepository;
    private final ProductService productService;


    public CategoryServiceImpl(ModelMapper modelMapper, CategoryRepository categoryRepository, ProductService productService) {
        this.modelMapper = modelMapper;
        this.categoryRepository = categoryRepository;
        this.productService = productService;
    }

    @Override
    public CategoryDto create(CategoryInputDto inputDto) {
        Category category = modelMapper.map(inputDto, Category.class);
        categoryRepository.save(category);

        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id) {
        return categoryRepository.getCategoryById(id);
    }

    @Override
    public List<CategoryProductsCountDto> categoriesReports() {
        List<CategoryProductsCountDto> result = new ArrayList<>();

        for (Category category : categoryRepository.findAll()) {
            CategoryProductsCountDto outputDto = new CategoryProductsCountDto();

            outputDto.setName(category.getName());
            int size = category.getProducts().size();

            if (size == 0) {
                outputDto.setProductsCount(0);
                outputDto.setTotalRevenue(BigDecimal.ZERO);
                outputDto.setAvgPrice(BigDecimal.ZERO); // Or null, depending on your logic
                result.add(outputDto);
                continue; // Move to the next category
            }

            BigDecimal sum = productService.totalPriceById(category.getId());
            outputDto.setProductsCount(size);
            outputDto.setTotalRevenue(sum);

            RoundingMode roundingMode = RoundingMode.HALF_UP;

            outputDto.setAvgPrice(sum.divide(new BigDecimal(size), 2, roundingMode));

            result.add(outputDto);
        }

        result.sort(Comparator.comparing(CategoryProductsCountDto::getProductsCount));
        return result;
    }
}
