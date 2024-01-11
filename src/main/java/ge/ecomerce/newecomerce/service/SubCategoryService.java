package ge.ecomerce.newecomerce.service;


import ge.ecomerce.newecomerce.entity.category.Subcategory;
import ge.ecomerce.newecomerce.model.SubcategoryModel;

import java.util.List;

public interface SubCategoryService {
    List<Subcategory> getAll();

    String saveSubCategory(SubcategoryModel subcategoryModel);
}
