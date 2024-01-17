package ge.ecomerce.newecomerce.service;

import ge.ecomerce.newecomerce.entity.Product;
import ge.ecomerce.newecomerce.model.request.ProductModel;
import ge.ecomerce.newecomerce.model.request.ProductsModel;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {
    Product saveNewProduct(ProductModel productModel);

    List<Product> saveNewProducts(ProductsModel productsModel);

    Page<Product> getALL(Integer pageNumber, Integer pageSize);

    Product getByID(Long productID);

    Page<Product> getByCategory(Long categoryID, Integer pageNumber, Integer pageSize);

    Page<Product> getBySubcategory(Long categoryID, Integer pageNumber, Integer pageSize);

    List<Product> getByName(String name);

    String deleteByID(Long productID);

    String deleteAll(Long subCategoryID);


    //todo add search by product name
}
