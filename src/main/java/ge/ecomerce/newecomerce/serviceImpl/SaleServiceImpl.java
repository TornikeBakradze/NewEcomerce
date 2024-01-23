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

import java.util.List;

@Service
@RequiredArgsConstructor
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    @Override
    public Sale addNewSale(SaleModel saleModel) {
        try {
            Sale sale = Sale.builder()
                    .saleInNumber(saleModel.getSaleInNumber())
                    .saleInPercent(saleModel.getSaleInPercent())
                    .startDate(saleModel.getStartDate())
                    .endDate(saleModel.getEndDate())
                    .build();
            Sale savedSale = saleRepository.save(sale);
            if (!saleModel.getProducts().isEmpty()) {
                for (Long productId : saleModel.getProducts()) {
                    Product product = productRepository
                            .findById(productId).orElseThrow(() -> new DataNotFoundException("Product not found"));
                    product.setSale(sale);
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
    public Sale getByID(Long saleId) {
        return saleRepository.findById(saleId).orElseThrow(() -> new DataNotFoundException("Sale not found"));
    }

    @Override
    public Sale updateSale(Long saleId, SaleModel saleModel) {
        try {
            Sale updateSale = saleRepository.findById(saleId).orElseThrow(() -> new DataNotFoundException("Sale not found"));
            updateSale.setSaleInNumber(saleModel.getSaleInNumber());
            updateSale.setSaleInPercent(saleModel.getSaleInPercent());
            updateSale.setStartDate(saleModel.getStartDate());
            updateSale.setEndDate(saleModel.getEndDate());
            Sale savedSale = saleRepository.saveAndFlush(updateSale);
            if (!saleModel.getProducts().isEmpty()) {
                for (Long productId : saleModel.getProducts()) {
                    Product product = productRepository
                            .findById(productId).orElseThrow(() -> new DataNotFoundException("Product not found"));
                    product.setSale(savedSale);
                }
            }
            return savedSale;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
