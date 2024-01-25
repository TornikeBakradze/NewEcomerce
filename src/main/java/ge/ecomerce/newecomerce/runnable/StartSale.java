package ge.ecomerce.newecomerce.runnable;

import ge.ecomerce.newecomerce.entity.product.Product;
import ge.ecomerce.newecomerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StartSale implements Runnable {

    private final ProductRepository productRepository;
    private final List<Product> productsList;

    @Override
    public void run() {
        productRepository.saveAll(productsList);
    }
}
