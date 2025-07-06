package softuni.exercise.bookshopsystem.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.exercise.bookshopsystem.dto.CategoryInputDto;
import softuni.exercise.bookshopsystem.entities.Category;
import softuni.exercise.bookshopsystem.repositories.CategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public Category createCategory(CategoryInputDto inputDto) {
        Category category = new Category();
        category.setName(inputDto.getName());

        return categoryRepository.save(category);
    }
}
