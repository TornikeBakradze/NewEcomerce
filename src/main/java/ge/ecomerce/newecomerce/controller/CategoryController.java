package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
