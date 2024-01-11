package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.entity.category.Subcategory;
import ge.ecomerce.newecomerce.model.SubcategoryModel;
import ge.ecomerce.newecomerce.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SubCategoryController {

    private static final String SUBCATEGORY_BASE_URL = "subCategory";


    private final SubCategoryService subCategoryService;

    @GetMapping(SUBCATEGORY_BASE_URL+"/getAllSubCategory")
    public List<Subcategory> getAll() {
        return subCategoryService.getAll();
    }


    @PostMapping(SUBCATEGORY_BASE_URL+"/addNewSubcategory")
    public ResponseEntity<String> saveNewSubCategory(@RequestBody SubcategoryModel subcategoryModel) {
        return new ResponseEntity<>(subCategoryService.saveSubCategory(subcategoryModel), HttpStatus.OK);
    }

}
