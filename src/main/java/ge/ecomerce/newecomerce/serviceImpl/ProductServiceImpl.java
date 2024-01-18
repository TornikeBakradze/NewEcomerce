package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.Product;
import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.entity.category.Subcategory;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.model.request.ProductModel;
import ge.ecomerce.newecomerce.model.request.ProductNameAndDescriptionModel;
import ge.ecomerce.newecomerce.model.request.ProductsModel;
import ge.ecomerce.newecomerce.model.respone.ReturnPage;
import ge.ecomerce.newecomerce.repository.CategoryRepository;
import ge.ecomerce.newecomerce.repository.ProductRepository;
import ge.ecomerce.newecomerce.repository.SubcategoryRepository;
import ge.ecomerce.newecomerce.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final SubcategoryRepository subcategoryRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Product saveNewProduct(ProductModel productModel) {
        Subcategory subcategory = subcategoryRepository.findById(productModel.getSubCategoryID())
                .orElseThrow(() -> new DataNotFoundException("Subcategory not found"));
        try {
            ProductNameAndDescriptionModel productDetails =
                    productModel.getProductNameAndDescriptionModel();
            Product product = Product.builder()
                    .name(productDetails.getName())
                    .price(productDetails.getPrice())
                    .quantity(productDetails.getQuantity())
                    .details(productDetails.getDetails())
                    .subcategory(subcategory)
                    .description(productDetails.getDescription())
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
                        .price(productModel.getPrice())
                        .quantity(productModel.getQuantity())
                        .details(productModel.getDetails())
                        .isActive(true)
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
    public Page<Product> getALL(Integer pageNumber, Integer pageSize) {
        try {
            Sort.by("createdDate").descending();
            PageRequest pageRequest = ReturnPage.buildPageRequest(pageNumber, pageSize, Sort.by("createdDate").descending());
            Page<Product> products = productRepository.getAll(pageRequest);
            if (products.isEmpty()) {
                throw new DataNotFoundException("No product found");
            } else
                return products;
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product getByID(Long productID) {
        return productRepository.getByProductId(productID).orElseThrow(() -> new DataNotFoundException(String.format("Product with %s id not found", productID)));
    }

    @Override
    public Page<Product> getByCategory(Long categoryID, Integer pageNumber, Integer pageSize) {
        try {
            Sort.by("createdDate").descending();
            PageRequest pageRequest = ReturnPage.buildPageRequest(pageNumber, pageSize, Sort.by("createdDate").descending());
            Page<Product> productByCategory = productRepository.getProductByCategory(categoryID, pageRequest);
            if (productByCategory.isEmpty()) {
                Category category = categoryRepository.findById(categoryID).orElseThrow(() -> new DataNotFoundException("Category not found"));
                throw new DataNotFoundException(String.format("%s category product does not exist", category.getName()));
            }
            return productByCategory;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<Product> getBySubcategory(Long subCategoryID, Integer pageNumber, Integer pageSize) {
        try {
            Sort.by("createdDate").descending();
            PageRequest pageRequest = ReturnPage.buildPageRequest(pageNumber, pageSize, Sort.by("createdDate").descending());
            Page<Product> productByCategory = productRepository.getProductBySubCategory(subCategoryID, pageRequest);
            if (productByCategory.isEmpty()) {
                Subcategory subCategory = subcategoryRepository.findById(subCategoryID).orElseThrow(() -> new DataNotFoundException("SubCategory not found"));
                throw new DataNotFoundException(String.format("%s subcategory product does not exist", subCategory.getName()));
            }
            return productByCategory;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> getByName(String name) {
        try {
            List<Product> byNameContaining = productRepository.getByName(name.replace(" ", ""));
            if (byNameContaining.isEmpty()) {
                throw new DataNotFoundException("Product not found");
            }
            return byNameContaining;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Product update(Long productID, ProductModel productModel) {
        Product product = productRepository.findById(productID).orElseThrow(() -> new DataNotFoundException("Product not found"));
        try {
            Subcategory subcategory =
                    subcategoryRepository.findById(productModel.getSubCategoryID()).orElseThrow(() -> new DataNotFoundException("Subcategory not found"));
            product.setName(productModel.getProductNameAndDescriptionModel().getName());
            product.setPrice(productModel.getProductNameAndDescriptionModel().getPrice());
            product.setQuantity(productModel.getProductNameAndDescriptionModel().getQuantity());
            product.setDetails(productModel.getProductNameAndDescriptionModel().getDetails());
            product.setDescription(productModel.getProductNameAndDescriptionModel().getDescription());
            product.setSubcategory(subcategory);

            return productRepository.save(product);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public String deleteByID(Long productID) {
        Product product = productRepository.findById(productID).orElseThrow(() -> new DataNotFoundException("Product not found"));
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
