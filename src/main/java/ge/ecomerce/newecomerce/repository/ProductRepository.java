package ge.ecomerce.newecomerce.repository;

import ge.ecomerce.newecomerce.entity.product.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("SELECT  p from Product p where p.isActive=true")
    Page<Product> getAll(Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.isActive = true AND p.id = :productID")
    Optional<Product> getByProductId(@Param("productID") Long productID);

    @Query("SELECT p FROM Product p WHERE p.isActive = true AND LOWER(p.name) LIKE LOWER(CONCAT('%', :productName, '%'))")
    List<Product> getByName(@Param("productName") String productName);

    List<Product> findByNameContaining(String name);

    @Query("SELECT p from Product p where p.subcategory.category.id=:categoryID and p.isActive=true")
    Page<Product> getProductByCategory(@Param("categoryID") Long categoryID, Pageable pageable);

    @Query("SELECT p from Product p where p.subcategory.category.id=:categoryID")
    Page<Product> getProductByCategoryWithoutActive(@Param("categoryID") Long categoryID, Pageable pageable);

    @Query("SELECT p from Product p where p.subcategory.id=:categoryID and p.isActive = true")
    Page<Product> getProductBySubCategory(@Param("categoryID") Long subCategoryID, Pageable pageable);

    @Query("SELECT p from Product p where p.subcategory.id=:categoryID")
    Page<Product> getProductBySubCategoryWithoutActive(@Param("categoryID") Long subCategoryID, Pageable pageable);

    @Query("select p from Product p where p.isActive=false")
    Page<Product> getInactiveProduct(Pageable pageable);

    @Query("DELETE FROM Product p where p.subcategory.id=:subcategoryID")
    @Modifying
    @Transactional
    void deleteAllByCategory(@Param("subcategoryID") Long subcategoryID);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.sale.id = null WHERE p.id = :productId")
    void updateSaleIdToNull(Long productId);

    @Modifying
    @Transactional
    @Query("UPDATE Product p SET p.sale.id = null WHERE p.sale.id = :saleId")
    void updateSaleToNullBySaleID(Long saleId);
}
