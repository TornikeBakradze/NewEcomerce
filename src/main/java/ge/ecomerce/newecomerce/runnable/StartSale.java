package ge.ecomerce.newecomerce.runnable;

import ge.ecomerce.newecomerce.entity.product.Product;
import ge.ecomerce.newecomerce.entity.product.Sale;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.repository.ProductRepository;
import ge.ecomerce.newecomerce.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
@Setter
public class StartSale implements Runnable {

    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    private Long saleId;

    @Override
    public void run() {
        Sale sale = saleRepository.findById(saleId).orElseThrow(() -> new DataNotFoundException("Sale not found"));
        startSale(sale);
    }


    public void startSale(Sale sale) {
        try {
            int i = sale.getStartDate().compareTo(LocalDateTime.now());
            if (i > 0) {
                long seconds = Duration.between(LocalDateTime.now(), sale.getStartDate()).getSeconds();
                Thread.sleep(seconds * 1000);
            }
            System.out.println(LocalDateTime.now());
            List<Product> products = productRepository.getByProductBySaleId(sale.getId());
            if (!products.isEmpty()) {
                for (Product product : products) {
                    Product startSaleProduct = updateProductPrice(product, sale);
                    productRepository.save(startSaleProduct);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public static Product updateProductPrice(Product product, Sale sale) {
        BigDecimal originalPrice = product.getPrice();
        if (sale.getSaleInNumber() != null) {
            BigDecimal updatedSalePrice = originalPrice.subtract(sale.getSaleInNumber());
            product.setSalePrice(updatedSalePrice);
        } else if (sale.getSaleInPercent() != null) {
            BigDecimal salePrice = calculatePercentageOfAmount(originalPrice, sale.getSaleInPercent());
            product.setSalePrice(salePrice);
        }
        return product;
    }

    private static BigDecimal calculatePercentageOfAmount(BigDecimal amount, int percentage) {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Amount cannot be zero.");
        }
        BigDecimal percentageDecimal = BigDecimal.valueOf(100 - percentage);
        BigDecimal result = amount.multiply(percentageDecimal.divide(new BigDecimal("100")));
        return result.setScale(2, BigDecimal.ROUND_HALF_UP);
    }

}
