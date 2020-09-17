package com.flbu920.blog.service;

import com.flbu920.blog.model.Category;
import com.flbu920.blog.util.BlogResult;

import java.util.List;

/**
 * @Author flbu920
 * @Date 2020/9/1
 */
public interface CategoryService {
    int  saveCategory(Category category);

    int  updateCategory(Category category);

    int  deleteCategoryById(Integer categoryId);

    Category getCategoryById(Integer categoryId);

    List<Category> getAllCategories();
}
