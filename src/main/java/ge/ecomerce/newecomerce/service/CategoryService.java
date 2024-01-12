package ge.ecomerce.newecomerce.service;

import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.entity.category.Subcategory;
import ge.ecomerce.newecomerce.model.CategoryModel;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Category getById(Long id);

    Category updateCategory(Long categoryId, CategoryModel name);

    Category saveCategory(CategoryModel categoryModel);

    String delete(Long id);
}

