package com.softuni.pathfinder.service;

import com.softuni.pathfinder.domain.entities.Category;
import com.softuni.pathfinder.domain.enums.CategoryName;
import com.softuni.pathfinder.repository.CategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Autowired
    public CategoryService(CategoryRepository categoryRepository,
                           ModelMapper modelMapper) {
        this.categoryRepository = categoryRepository;
        this.modelMapper = modelMapper;
    }

    public Category findByName(CategoryName categoryName) {
        return this.categoryRepository.findByName(categoryName)
                .orElseThrow();
    }
}
