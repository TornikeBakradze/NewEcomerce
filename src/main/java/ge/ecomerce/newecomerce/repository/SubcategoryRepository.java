package ge.ecomerce.newecomerce.repository;

import ge.ecomerce.newecomerce.entity.category.Subcategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubcategoryRepository extends JpaRepository<Subcategory, Long> {
}
