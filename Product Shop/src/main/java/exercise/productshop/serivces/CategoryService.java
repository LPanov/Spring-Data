package exercise.productshop.serivces;

import exercise.productshop.dtos.CategoryDto;
import exercise.productshop.dtos.CategoryInputDto;
import exercise.productshop.dtos.CategoryProductsCountDto;
import exercise.productshop.entities.Category;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
public interface CategoryService {
    CategoryDto create(CategoryInputDto inputDto);

    List<Category> findAll();

    Category findById(Long id);

    List<CategoryProductsCountDto> categoriesReports();
}
