package ge.ecomerce.newecomerce.service;

import ge.ecomerce.newecomerce.entity.Product;
import ge.ecomerce.newecomerce.model.request.ProductModel;
import ge.ecomerce.newecomerce.model.request.ProductsModel;

import java.util.List;

public interface ProductService {
    Product saveNewProduct(ProductModel productModel);

    List<Product> saveNewProducts(ProductsModel productsModel);

    List<Product> getALL();

    Product getByID(Long productID);

    String deleteByID(Long productID);

    String deleteAll(Long subCategoryID);
}
