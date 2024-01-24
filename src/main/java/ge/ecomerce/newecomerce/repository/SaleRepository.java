package ge.ecomerce.newecomerce.repository;

import ge.ecomerce.newecomerce.entity.product.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("SELECT s FROM Sale s WHERE s.startDate BETWEEN :startDateTime AND :endDateTime")
    List<Sale> findAllStartSale(@Param("startDateTime") LocalDateTime startDateTime, @Param("endDateTime") LocalDateTime endDateTime);

    @Query("SELECT s FROM Sale s WHERE s.endDate BETWEEN :startDateTime AND :endDateTime")
    List<Sale> findAllEndingSale(
            @Param("startDateTime") LocalDateTime startDateTime,
            @Param("endDateTime") LocalDateTime endDateTime
    );


    @Modifying
    @Query("UPDATE Sale s SET s.saleInPercent = :newPrice WHERE s.id = :saleID")
    void updateSalePercent(@Param("saleID") Long saleID, @Param("newPrice") Integer newPrice);

    @Modifying
    @Query("UPDATE Sale s SET s.saleInNumber = :newPrice WHERE s.id = :saleID")
    void updateSalePriceInNumber(@Param("saleID") Long saleID, @Param("newPrice") BigDecimal newPrice);
}
