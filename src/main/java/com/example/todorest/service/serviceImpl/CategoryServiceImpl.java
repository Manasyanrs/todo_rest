package com.example.todorest.service.serviceImpl;

import com.example.todorest.entity.Category;
import com.example.todorest.repository.CategoryRepository;
import com.example.todorest.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Category saveCategoryByName(String categoryName) {
        if (!categoryName.equals("")){
            Category category = new Category();
            category.setName(categoryName);
            return categoryRepository.save(category);

        }
        return null;
    }
}
