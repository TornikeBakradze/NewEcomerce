package ge.ecomerce.newecomerce.repository;

import ge.ecomerce.newecomerce.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("DELETE FROM Product p where p.subcategory.id=:subcategoryID")
    @Modifying
    @Transactional
    void deleteAllByCategory(@Param("subcategoryID") Long subcategoryID);
}
