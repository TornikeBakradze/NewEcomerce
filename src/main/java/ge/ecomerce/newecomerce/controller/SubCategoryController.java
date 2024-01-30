package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.entity.category.Subcategory;
import ge.ecomerce.newecomerce.model.request.SubcategoriesModel;
import ge.ecomerce.newecomerce.model.request.SubcategoryModel;
import ge.ecomerce.newecomerce.service.SubCategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubCategoryController {

    private static final String SUBCATEGORY_BASE_URL = "subCategory";


    private final SubCategoryService subCategoryService;

    @GetMapping(SUBCATEGORY_BASE_URL + "/getAllSubCategory")
    public List<Subcategory> getAll() {
        return subCategoryService.getAll();
    }

    @GetMapping(SUBCATEGORY_BASE_URL + "/{findById}")
    public ResponseEntity<Subcategory> getById(@PathVariable("findById") Long categoryID) {
        return new ResponseEntity<>(subCategoryService.getSubCategoryById(categoryID), HttpStatus.OK);
    }

    @PostMapping(SUBCATEGORY_BASE_URL + "/addNewSubcategory")
    public ResponseEntity<Subcategory> saveNewSubCategory(@RequestBody @Validated SubcategoryModel subcategoryModel) {
        return new ResponseEntity<>(subCategoryService.saveSubCategory(subcategoryModel), HttpStatus.CREATED);
    }

    @PostMapping(SUBCATEGORY_BASE_URL + "/addNewSubCategories")
    private ResponseEntity<List<Subcategory>> saveNewSubcategories(
            @RequestBody @Validated SubcategoriesModel subcategoriesModel) {
        return new ResponseEntity<>(subCategoryService.saveSubCategories(subcategoriesModel), HttpStatus.OK);
    }


    @DeleteMapping(SUBCATEGORY_BASE_URL + "/delete/{subCategoryID}")
    public ResponseEntity<String> deleteByID(@PathVariable("subCategoryID") Long subCategoryID) {
        return new ResponseEntity<>(subCategoryService.deleteSubcategoryByID(subCategoryID), HttpStatus.OK);
    }

    @DeleteMapping(SUBCATEGORY_BASE_URL + "/deleteByCategoryId/{categoryID}")
    public ResponseEntity<String> deleteByCategoryId(@PathVariable("categoryID") Long categoryID) {
        return new ResponseEntity<>(subCategoryService.deleteAllByCategoryID(categoryID), HttpStatus.OK);
    }

    @PutMapping(SUBCATEGORY_BASE_URL + "/update/{subCategoryID}")
    public ResponseEntity<Subcategory> update(@PathVariable("subCategoryID") Long subCategoryID,
                                              @RequestBody @Validated SubcategoryModel subcategoryModel) {
        return new ResponseEntity<>(subCategoryService.updateCategory(subCategoryID, subcategoryModel), HttpStatus.OK);
    }

}
