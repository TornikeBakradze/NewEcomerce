package ge.ecomerce.newecomerce.service;

import ge.ecomerce.newecomerce.entity.product.Product;
import ge.ecomerce.newecomerce.model.request.ProductModel;
import ge.ecomerce.newecomerce.model.request.ProductsModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product saveNewProduct(ProductModel productModel);

    List<Product> saveNewProducts(ProductsModel productsModel);

    Page<Product> getALL(Integer pageNumber, Integer pageSize);

    Page<Product> getALLWithoutActiveStatus(Integer pageNumber, Integer pageSize);

    Product getByID(Long productID);

    Product getByIDWithoutActiveStatus(Long productID);

    Page<Product> getByCategory(Long categoryID, Integer pageNumber, Integer pageSize);

    Page<Product> getByCategoryWithoutActive(Long categoryID, Integer pageNumber, Integer pageSize);

    Page<Product> getBySubcategory(Long subCategoryID, Integer pageNumber, Integer pageSize);

    Page<Product> getBySubcategoryWithoutActive(Long subCategoryID, Integer pageNumber, Integer pageSize);

    List<Product> getByName(String name);

    List<Product> getByNameWithoutActive(String name);

    Page<Product> getInactiveProduct(Integer pageNumber, Integer pageSize);

    Product update(Long productID, ProductModel productModel);

    String deleteByID(Long productID);

    String deleteAll(Long subCategoryID);

    String changeActiveStatus(Long productID);

    Product addSale(Long productID, Long saleID);

    String deleteSale(Long productID);

}
