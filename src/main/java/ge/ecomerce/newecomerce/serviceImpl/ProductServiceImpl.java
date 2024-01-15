package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.Product;
import ge.ecomerce.newecomerce.entity.category.Subcategory;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.model.request.ProductModel;
import ge.ecomerce.newecomerce.model.request.ProductNameAndDescriptionModel;
import ge.ecomerce.newecomerce.model.request.ProductsModel;
import ge.ecomerce.newecomerce.repository.ProductRepository;
import ge.ecomerce.newecomerce.repository.SubcategoryRepository;
import ge.ecomerce.newecomerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Override
    public Product saveNewProduct(ProductModel productModel) {
        Subcategory subcategory = subcategoryRepository.findById(productModel.getSubCategoryID())
                .orElseThrow(() -> new DataNotFoundException("Subcategory not found"));
        try {
            Product product = Product.builder()
                    .name(productModel.getProductNameAndDescriptionModel().getName())
                    .subcategory(subcategory)
                    .description(productModel.getProductNameAndDescriptionModel().getDescription())
                    .build();
            return productRepository.save(product);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(
                    String.format("Product with name %s already exist", productModel.getProductNameAndDescriptionModel().getName()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> saveNewProducts(ProductsModel productsModel) {
        String globalProductName = null;
        Subcategory subcategory = subcategoryRepository.findById(productsModel.getSubCategoryID())
                .orElseThrow(() -> new DataNotFoundException("Subcategory not found"));
        List<Product> productList = new ArrayList<>();
        try {
            List<ProductNameAndDescriptionModel> productNameAndDescriptionModelList = productsModel.getProductNameAndDescriptionModelList();
            for (ProductNameAndDescriptionModel productModel : productNameAndDescriptionModelList) {
                Product product = Product.builder()
                        .name(productModel.getName())
                        .description(productModel.getDescription())
                        .subcategory(subcategory)
                        .build();
                globalProductName = productModel.getName();
                productList.add(productRepository.save(product));
            }
            return productList;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException(
                    String.format("Product with name %s already exist", globalProductName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public List<Product> getALL() {
        return null;
    }

    @Override
    public Product getByID(Long productID) {
        return null;
    }

    @Override
    public String deleteByID(Long productID) {
        Product product = productRepository.findById(productID).orElseThrow(() -> new DataNotFoundException("Product not find"));
        try {
            productRepository.delete(product);
            return String.format("%s deleted successfully", product.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteAll(Long subCategoryID) {
        Subcategory subcategory = subcategoryRepository.findById(subCategoryID).orElseThrow(() -> new DataNotFoundException("Subcategory not find"));
        try {
            productRepository.deleteAllByCategory(subCategoryID);
            return String.format("%s all product deleted", subcategory.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
