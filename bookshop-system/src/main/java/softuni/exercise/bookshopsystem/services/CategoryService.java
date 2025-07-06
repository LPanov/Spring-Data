package softuni.exercise.bookshopsystem.services;

import softuni.exercise.bookshopsystem.dto.CategoryInputDto;
import softuni.exercise.bookshopsystem.entities.Category;

public interface CategoryService {
    Category createCategory(CategoryInputDto inputDto);
}
