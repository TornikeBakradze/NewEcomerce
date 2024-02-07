package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.model.request.CategoriesModel;
import ge.ecomerce.newecomerce.model.request.CategoryModel;
import ge.ecomerce.newecomerce.model.respone.CategoryWithSubcategories;
import ge.ecomerce.newecomerce.service.CategoryService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/category")
public class CategoryController {

    private static final String CATEGORY_BASE_URL = "/s";

    private final CategoryService categoryService;

    @GetMapping(CATEGORY_BASE_URL + "/getAllCategory")
    public ResponseEntity<List<Category>> getAllCategory() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping(CATEGORY_BASE_URL + "/{categoryID}")
    public ResponseEntity<Category> getById(@PathVariable("categoryID") Long categoryID) {
        return new ResponseEntity<>(categoryService.getById(categoryID), HttpStatus.OK);
    }

    @GetMapping(CATEGORY_BASE_URL + "/getCategoryWithSubCategoryByCategoryID")
    public ResponseEntity<CategoryWithSubcategories> getCategoryWithSubcategory(@RequestParam Long categoryID) {
        return new ResponseEntity<>(categoryService.categoryWithSubcategoryByCategoryID(categoryID), HttpStatus.OK);
    }

    @GetMapping(CATEGORY_BASE_URL + "/getCategoryWithSubCategoryByCategories")
    public ResponseEntity<List<CategoryWithSubcategories>> getCategoryWithSubcategories() {
        return new ResponseEntity<>(categoryService.categoryWithSubcategoryByCategories(), HttpStatus.OK);
    }

    @PutMapping(CATEGORY_BASE_URL + "/update")
    public ResponseEntity<Category> update(@RequestParam Long categoryID,
                                           @RequestBody CategoryModel categoryModel) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryID, categoryModel), HttpStatus.OK);
    }

    @PostMapping(CATEGORY_BASE_URL + "/save")
    public ResponseEntity<Category> saveNewCategory(@RequestBody @Validated CategoryModel categoryModel) {
        return new ResponseEntity<>(categoryService.saveCategory(categoryModel), HttpStatus.CREATED);
    }

    @PostMapping(CATEGORY_BASE_URL + "/saveCategories")
    public ResponseEntity<List<Category>> saveCategories(@RequestBody @Validated CategoriesModel categoriesModel) {
        return new ResponseEntity<>(categoryService.saveCategories(categoriesModel), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{categoryID}")
    public ResponseEntity<String> delete(@PathVariable("categoryID") Long categoryID) {
        return new ResponseEntity<>(categoryService.delete(categoryID), HttpStatus.OK);
    }

    @DeleteMapping( "/deleteAll")
    public ResponseEntity<String> deleteAll() {
        return new ResponseEntity<>(categoryService.deleteAll(), HttpStatus.OK);
    }

}
