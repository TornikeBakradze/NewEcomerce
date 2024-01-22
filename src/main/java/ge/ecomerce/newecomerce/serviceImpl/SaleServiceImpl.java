package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.product.Product;
import ge.ecomerce.newecomerce.entity.product.Sale;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.model.request.SaleModel;
import ge.ecomerce.newecomerce.repository.ProductRepository;
import ge.ecomerce.newecomerce.repository.SaleRepository;
import ge.ecomerce.newecomerce.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    @Override
    public Sale addNewSale(SaleModel saleModel) {
        try {
            Set<Product> products = new HashSet<>();
            for (Long productId : saleModel.getProducts()) {
                Product product = productRepository
                        .findById(productId).orElseThrow(() -> new DataNotFoundException("Product not found"));
                products.add(product);
            }
            Sale sale = Sale.builder()
                    .saleInNumber(saleModel.getSaleInNumber())
                    .saleInPercent(saleModel.getSaleInPercent())
                    .startDate(saleModel.getStartDate())
                    .endDate(saleModel.getEndDate())
                    .products(products)
                    .build();
            Sale savedSale = saleRepository.save(sale);
            if (saleModel.getSaleInNumber() != null) {
                for (Long productID : saleModel.getProducts()) {
                    Product product = productRepository
                            .findById(productID).orElseThrow(() -> new DataNotFoundException("Product not found"));
                    product.setSalePrice(product.getPrice().subtract(saleModel.getSaleInNumber()));
                    product.setSale(savedSale);

                    productRepository.save(product);
                }
            }
            return savedSale;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Sale> getALLSale() {
        return saleRepository.findAll();
    }

    @Override
    public Sale getByID() {
        return null;
    }
}
