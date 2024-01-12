package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.model.CategoryModel;
import ge.ecomerce.newecomerce.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
public class CategoryController {

    private static final String CATEGORY_BASE_URL = "category";

    private final CategoryService categoryService;

    @GetMapping(CATEGORY_BASE_URL + "/getAllCategory")
    public ResponseEntity<List<Category>> getAllCategory() {
        return new ResponseEntity<>(categoryService.getAll(), HttpStatus.OK);
    }

    @GetMapping(CATEGORY_BASE_URL + "/getById")
    public ResponseEntity<Category> getById(@RequestParam Long categoryID) {
        return new ResponseEntity<>(categoryService.getById(categoryID), HttpStatus.OK);
    }

    @PutMapping(CATEGORY_BASE_URL + "/update")
    public ResponseEntity<Category> update(@RequestParam Long categoryID, @RequestBody CategoryModel categoryModel) {
        return new ResponseEntity<>(categoryService.updateCategory(categoryID, categoryModel), HttpStatus.OK);
    }

    @PostMapping(CATEGORY_BASE_URL + "/save")
    private ResponseEntity<Category> saveNewCategory(@RequestBody CategoryModel categoryModel) {
        return new ResponseEntity<>(categoryService.saveCategory(categoryModel), HttpStatus.CREATED);
    }

    @DeleteMapping(CATEGORY_BASE_URL + "/delete")
    private ResponseEntity<String> delete(@RequestParam Long categoryID) {
        return new ResponseEntity<>(categoryService.delete(categoryID), HttpStatus.OK);
    }

}
