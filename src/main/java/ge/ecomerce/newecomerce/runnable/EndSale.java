package ge.ecomerce.newecomerce.runnable;

import ge.ecomerce.newecomerce.entity.product.Product;
import ge.ecomerce.newecomerce.entity.product.Sale;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.repository.ProductRepository;
import ge.ecomerce.newecomerce.repository.SaleRepository;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Setter
@Slf4j
public class EndSale implements Runnable {
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    private Long saleId;

    @Override
    public void run() {
        Sale sale = saleRepository.findById(saleId).orElseThrow(() -> new DataNotFoundException("Sale not found"));
        andSale(sale);
    }

    public void andSale(Sale sale) {
        log.info("Anda sale");
        List<Product> products = productRepository.getByProductBySaleId(sale.getId());
        if (!products.isEmpty()) {
            for (Product product : products) {
                product.setSale(null);
                product.setSalePrice(null);
                productRepository.save(product);
            }
        }
    }


}
