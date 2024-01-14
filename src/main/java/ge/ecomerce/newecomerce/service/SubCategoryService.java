package ge.ecomerce.newecomerce.service;


import ge.ecomerce.newecomerce.entity.category.Subcategory;
import ge.ecomerce.newecomerce.model.request.SubcategoryModel;

import java.util.List;

public interface SubCategoryService {
    List<Subcategory> getAll();

    Subcategory getSubCategoryByName(String name);

    Subcategory updateCategory(Long id,SubcategoryModel subcategoryModel);

    String saveSubCategory(SubcategoryModel subcategoryModel);

    String deleteSubcategoryByID(Long id);
}
