package ge.ecomerce.newecomerce.service;

import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.model.request.CategoriesModel;
import ge.ecomerce.newecomerce.model.request.CategoryModel;
import ge.ecomerce.newecomerce.model.respone.CategoryWithSubcategories;

import java.util.List;

public interface CategoryService {
    List<Category> getAll();

    Category getById(Long id);

    Category updateCategory(Long categoryId, CategoryModel name);

    Category saveCategory(CategoryModel categoryModel);

    List<Category> saveCategories(CategoriesModel categoriesModel);

    String delete(Long id);

    String deleteAll();

    CategoryWithSubcategories categoryWithSubcategoryByCategoryID(Long categoryID);

    List<CategoryWithSubcategories> categoryWithSubcategoryByCategories();
}

