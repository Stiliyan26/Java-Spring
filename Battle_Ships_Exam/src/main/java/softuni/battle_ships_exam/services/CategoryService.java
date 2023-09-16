package softuni.battle_ships_exam.services;

import jakarta.annotation.PostConstruct;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.battle_ships_exam.domain.entities.Category;
import softuni.battle_ships_exam.domain.enums.CategoryType;
import softuni.battle_ships_exam.domain.model.CategoryModel;
import softuni.battle_ships_exam.repository.CategoryRepository;

import java.util.Arrays;
import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository, ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    @PostConstruct
    private void postConstruct() {
        if (this.categoryRepository.count() == 0) {
            List<Category> categories = Arrays.stream(CategoryType.values())
                    .map(enumValue -> CategoryModel.builder()
                            .name(enumValue)
                            .description("fight me")
                            .build())
                    .map(categoryModel -> this.modelMapper.map(categoryModel, Category.class))
                    .toList();

            this.categoryRepository.saveAllAndFlush(categories);
        }
    }
}
