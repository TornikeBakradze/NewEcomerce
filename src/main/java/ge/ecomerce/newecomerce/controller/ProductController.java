package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.entity.product.Product;
import ge.ecomerce.newecomerce.model.request.ProductModel;
import ge.ecomerce.newecomerce.model.request.ProductsModel;
import ge.ecomerce.newecomerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping(PRODUCT_BASE_URL + "/allProduct")
    private ResponseEntity<Page<Product>> allProduct(@RequestParam(required = false) Integer pageNumber,
                                                     @RequestParam(required = false) Integer pageSize) {
        return new ResponseEntity<>(productService.getALL(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping(PRODUCT_BASE_URL + "/getWithoutActive/allProduct")
    private ResponseEntity<Page<Product>> getALLWithoutActiveStatus(@RequestParam(required = false) Integer pageNumber,
                                                                    @RequestParam(required = false) Integer pageSize) {
        return new ResponseEntity<>(productService.getALLWithoutActiveStatus(pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping(PRODUCT_BASE_URL + "/{productID}")
    private ResponseEntity<Product> getByID(@PathVariable("productID") Long productID) {
        return new ResponseEntity<>(productService.getByID(productID), HttpStatus.OK);
    }

    @GetMapping(PRODUCT_BASE_URL + "/getWithoutActive/{productID}")
    private ResponseEntity<Product> getByIDWithoutActiveStatus(@PathVariable("productID") Long productID) {
        return new ResponseEntity<>(productService.getByIDWithoutActiveStatus(productID), HttpStatus.OK);
    }

    @GetMapping(PRODUCT_BASE_URL + "/byCategory/{categoryID}")
    private ResponseEntity<Page<Product>> getByCategory(@RequestParam(required = false) Integer pageNumber,
                                                        @RequestParam(required = false) Integer pageSize,
                                                        @PathVariable("categoryID") Long categoryID) {
        return new ResponseEntity<>(productService.getByCategory(categoryID, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping(PRODUCT_BASE_URL + "/getWithoutActive/byCategory/{categoryID}")
    private ResponseEntity<Page<Product>> getByCategoryWithoutActive(@RequestParam(required = false) Integer pageNumber,
                                                                     @RequestParam(required = false) Integer pageSize,
                                                                     @PathVariable("categoryID") Long categoryID) {
        return new ResponseEntity<>(productService.getByCategoryWithoutActive(categoryID, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping(PRODUCT_BASE_URL + "bySubCategory/{subCategoryID}")
    private ResponseEntity<Page<Product>> getBySubCategory(@RequestParam(required = false) Integer pageNumber,
                                                           @RequestParam(required = false) Integer pageSize,
                                                           @PathVariable("subCategoryID") Long subCategoryID) {
        return new ResponseEntity<>(productService.getBySubcategory(subCategoryID, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping(PRODUCT_BASE_URL + "/getWithoutActive/bySubCategory/{subCategoryID}")
    private ResponseEntity<Page<Product>> getBySubcategoryWithoutActive(@RequestParam(required = false) Integer pageNumber,
                                                                        @RequestParam(required = false) Integer pageSize,
                                                                        @PathVariable("subCategoryID") Long subCategoryID) {
        return new ResponseEntity<>(productService.getBySubcategoryWithoutActive(subCategoryID, pageNumber, pageSize), HttpStatus.OK);
    }

    @GetMapping(PRODUCT_BASE_URL + "/byName/{productName}")
    private ResponseEntity<List<Product>> getByName(@PathVariable("productName") String productName) {
        return new ResponseEntity<>(productService.getByName(productName), HttpStatus.OK);
    }

    @GetMapping(PRODUCT_BASE_URL + "/getWithoutActive/byName/{productName}")
    private ResponseEntity<List<Product>> getByNameWithoutActive(@PathVariable("productName") String productName) {
        return new ResponseEntity<>(productService.getByNameWithoutActive(productName), HttpStatus.OK);
    }

    @GetMapping(PRODUCT_BASE_URL + "/inactiveProduct")
    private ResponseEntity<Page<Product>> getInactiveProduct(@RequestParam(required = false) Integer pageNumber,
                                                             @RequestParam(required = false) Integer pageSize) {
        return new ResponseEntity<>(productService.getInactiveProduct(pageNumber, pageSize), HttpStatus.OK);
    }


    @PostMapping(PRODUCT_BASE_URL + "/addNewProduct")
    private ResponseEntity<Product> saveNew(@RequestBody @Validated ProductModel productModel) {
        return new ResponseEntity<>(productService.saveNewProduct(productModel), HttpStatusCode.valueOf(200));
    }

    @PostMapping(PRODUCT_BASE_URL + "/addNewProducts")
    private ResponseEntity<List<Product>> saveNewProducts(@RequestBody @Validated ProductsModel productsModel) {
        return new ResponseEntity<>(productService.saveNewProducts(productsModel), HttpStatus.OK);
    }

    @PutMapping(PRODUCT_BASE_URL + "/update/{productID}")
    private ResponseEntity<Product> update(@PathVariable("productID") Long productID,
                                           @RequestBody @Validated ProductModel productModel) {
        return new ResponseEntity<>(productService.update(productID, productModel), HttpStatus.OK);
    }

    @DeleteMapping(PRODUCT_BASE_URL + "/deleteByID/{productID}")
    private ResponseEntity<String> deleteByID(@RequestParam("productID") Long productID) {
        return new ResponseEntity<>(productService.deleteByID(productID), HttpStatus.OK);
    }

    @DeleteMapping(PRODUCT_BASE_URL + "/deleteAllBySubcategory/{subcategoryID}")
    private ResponseEntity<String> deleteAllBySubcategory(@RequestParam("subcategoryID") Long subcategoryID) {
        return new ResponseEntity<>(productService.deleteAll(subcategoryID), HttpStatus.OK);
    }


    @PutMapping(PRODUCT_BASE_URL + "/changeActiveStatus/{productID}")
    private ResponseEntity<String> changeActiveStatus(@PathVariable("productID") Long productID) {
        return new ResponseEntity<>(productService.changeActiveStatus(productID), HttpStatus.OK);
    }

    @PostMapping(PRODUCT_BASE_URL + "/addSale/{productID}/{saleID}")
    private ResponseEntity<Product> addSale(@PathVariable("productID") Long productID,
                                            @PathVariable("saleID") Long saleID) {
        return new ResponseEntity<>(productService.addSale(productID, saleID), HttpStatus.OK);
    }

    @DeleteMapping(PRODUCT_BASE_URL + "/deleteSale/{productID}")
    private ResponseEntity<String> deleteSale(@PathVariable("productID") Long productID) {
        return new ResponseEntity<>(productService.deleteSale(productID), HttpStatus.OK);
    }

}
