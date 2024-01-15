package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.entity.Product;
import ge.ecomerce.newecomerce.model.request.ProductModel;
import ge.ecomerce.newecomerce.model.request.ProductsModel;
import ge.ecomerce.newecomerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ProductController {

    private static final String PRODUCT_BASE_URL = "/product";
    private final ProductService productService;

    @PostMapping(PRODUCT_BASE_URL + "/addNewProduct")
    private ResponseEntity<Product> saveNew(@RequestBody @Validated ProductModel productModel) {
        return new ResponseEntity<>(productService.saveNewProduct(productModel), HttpStatusCode.valueOf(200));
    }

    @PostMapping(PRODUCT_BASE_URL + "/addNewProducts")
    private ResponseEntity<List<Product>> saveNewProducts(@RequestBody @Validated ProductsModel productsModel) {
        return new ResponseEntity<>(productService.saveNewProducts(productsModel), HttpStatus.OK);
    }

    @DeleteMapping(PRODUCT_BASE_URL + "/deleteByID/{productID}")
    private ResponseEntity<String> deleteByID(@RequestParam("productID") Long productID) {
        return new ResponseEntity<>(productService.deleteByID(productID), HttpStatus.OK);
    }

    @DeleteMapping(PRODUCT_BASE_URL + "/deleteAllBySubcategory/{subcategoryID}")
    private ResponseEntity<String> deleteAllBySubcategory(@RequestParam("subcategoryID") Long subcategoryID) {
        return new ResponseEntity<>(productService.deleteAll(subcategoryID), HttpStatus.OK);
    }

}
