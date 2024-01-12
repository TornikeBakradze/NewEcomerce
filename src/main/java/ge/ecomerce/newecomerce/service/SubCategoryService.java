package ge.ecomerce.newecomerce.service;


import ge.ecomerce.newecomerce.entity.category.Subcategory;
import ge.ecomerce.newecomerce.model.SubcategoryModel;

import java.util.List;
import java.util.Optional;

public interface SubCategoryService {
    List<Subcategory> getAll();

    Subcategory getSubCategoryByName(String name);

    Subcategory updateCategory(Long id,SubcategoryModel subcategoryModel);

    String saveSubCategory(SubcategoryModel subcategoryModel);

    String deleteSubcategoryByID(Long id);
}
