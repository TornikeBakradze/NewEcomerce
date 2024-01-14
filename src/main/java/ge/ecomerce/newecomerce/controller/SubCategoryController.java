package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.entity.category.Subcategory;
import ge.ecomerce.newecomerce.model.request.SubcategoryModel;
import ge.ecomerce.newecomerce.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping(SUBCATEGORY_BASE_URL +"/findByName")
    public ResponseEntity<Subcategory> getByName(@RequestParam(required = true) String name) {
        return new ResponseEntity<>(subCategoryService.getSubCategoryByName(name), HttpStatus.OK);
    }

    @PostMapping(SUBCATEGORY_BASE_URL + "/addNewSubcategory")
    public ResponseEntity<String> saveNewSubCategory(@RequestBody SubcategoryModel subcategoryModel) {
        return new ResponseEntity<>(subCategoryService.saveSubCategory(subcategoryModel), HttpStatus.CREATED);
    }

    @DeleteMapping(SUBCATEGORY_BASE_URL +"/deleteByID")
    public ResponseEntity<String> deleteByID(@RequestParam Long subCategoryID) {
        return new ResponseEntity<>(subCategoryService.deleteSubcategoryByID(subCategoryID), HttpStatus.OK);
    }

    @PutMapping(SUBCATEGORY_BASE_URL +"/update")
    public ResponseEntity<Subcategory> update(@RequestParam Long subCategoryID,
                                              @RequestBody SubcategoryModel subcategoryModel) {
        return new ResponseEntity<>(subCategoryService.updateCategory(subCategoryID, subcategoryModel), HttpStatus.OK);
    }

}
