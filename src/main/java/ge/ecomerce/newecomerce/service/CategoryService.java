package ge.ecomerce.newecomerce.service;

import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.model.request.CategoryModel;
import ge.ecomerce.newecomerce.model.respone.CategoryWithSubcategories;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Category getById(Long id);

    Category updateCategory(Long categoryId, CategoryModel name);

    Category saveCategory(CategoryModel categoryModel);

    String delete(Long id);

    CategoryWithSubcategories categoryWithSubcategoryByCategoryID(Long categoryID);

    List<CategoryWithSubcategories> categoryWithSubcategoryByCategories();
}

