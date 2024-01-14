package ge.ecomerce.newecomerce.repository;

import ge.ecomerce.newecomerce.entity.category.Subcategory;
import ge.ecomerce.newecomerce.model.respone.CategoryWithSubcategories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
    Optional<Subcategory> findByName(String name);

    @Query("SELECT s.name FROM Subcategory s where s.category.id=:categoryID")
    List<String> getCategoryWithSubCategoryByID(@Param("categoryID") Long categoryID);

    @Query("DELETE FROM Subcategory s where s.category.id=:categoryID")
    @Modifying
    @Transactional
    void deleteByCategoryId(@Param("categoryID") Long categoryID);

}
