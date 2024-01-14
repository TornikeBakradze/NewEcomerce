package ge.ecomerce.newecomerce.service;


import ge.ecomerce.newecomerce.entity.category.Subcategory;
import ge.ecomerce.newecomerce.model.request.SubcategoriesModel;
import ge.ecomerce.newecomerce.model.request.SubcategoryModel;

import java.util.List;

public interface SubCategoryService {
    List<Subcategory> getAll();

    Subcategory getSubCategoryById(Long id);

    Subcategory updateCategory(Long id, SubcategoryModel subcategoryModel);

    Subcategory saveSubCategory(SubcategoryModel subcategoryModel);

    List<Subcategory> saveSubCategories(SubcategoriesModel subcategoriesModel);

    String deleteSubcategoryByID(Long id);

    String deleteAll(Long categoryID);
}
