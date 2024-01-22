package ge.ecomerce.newecomerce.repository;

import ge.ecomerce.newecomerce.entity.product.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale,Long> {
}
