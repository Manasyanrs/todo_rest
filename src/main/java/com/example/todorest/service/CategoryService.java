package com.example.todorest.service;

import com.example.todorest.entity.Category;

public interface CategoryService {
    Category saveCategoryByName(String categoryName);
}
