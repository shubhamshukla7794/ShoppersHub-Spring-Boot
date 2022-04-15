package com.shubham.shoppershub.service;

import com.shubham.shoppershub.entity.Category;

import java.util.List;

public interface CategoryService {
    public List<Category> findAllCategories();
    public void saveCategory(Category category);
    public void deleteCategoryById(Long id);
    public Category findCategoryById(Long id);
}
