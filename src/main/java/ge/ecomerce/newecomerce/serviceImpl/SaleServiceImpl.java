package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.product.Product;
import ge.ecomerce.newecomerce.entity.product.Sale;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.model.request.SaleModel;
import ge.ecomerce.newecomerce.repository.ProductRepository;
import ge.ecomerce.newecomerce.repository.SaleRepository;
import ge.ecomerce.newecomerce.service.SaleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaleServiceImpl implements SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;
    private Long oldSaleCounter = 0L;
    private Long saleCounter = 0L;


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
            saleCounter++;
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

    @Override
    public String deleteSale(Long saleID) {
        try {
            Sale sale = saleRepository.findById(saleID).orElseThrow(() -> new DataNotFoundException("Sale not found"));
            productRepository.updateSaleToNullBySaleID(saleID);
            saleRepository.delete(sale);
            return String.format("Sale with id %s is deleted", saleID);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    @Scheduled(fixedRate = 20 * 1000)
    @Transactional
    public void startSale() {
        try {
            if (oldSaleCounter < saleCounter) {
                LocalDateTime startTime = LocalDateTime.now().minusMinutes(1);
                LocalDateTime endTime = LocalDateTime.now().plusMinutes(1);

                List<Sale> activeSales = saleRepository.findAllStartSale(startTime, endTime);

                if (!activeSales.isEmpty()) {
                    for (Sale sale : activeSales) {
                        List<Product> products = productRepository.getByProductBySaleId(sale.getId());

                        if (!products.isEmpty()) {
                            for (Product product : products) {
                                updateProductPrice(product, sale, "Start");
                            }
                            oldSaleCounter++;
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("Error in scheduled task", e);
        }
    }

    @Scheduled(fixedRate = 20 * 1000)
    @Transactional
    public void endSale() {
        try {
            ss();
            LocalDateTime startTime = LocalDateTime.now().minusMinutes(1);
            LocalDateTime endTime = LocalDateTime.now().plusMinutes(1);

            List<Sale> allEndingSale = saleRepository.findAllEndingSale(startTime, endTime);
            if (!allEndingSale.isEmpty()) {
                for (Sale sale : allEndingSale) {
                    List<Product> products = productRepository.getByProductBySaleId(sale.getId());
                    if (!products.isEmpty()) {
                        for (Product product : products) {
                            updateProductPrice(product, sale, "End");
                        }
                    }
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private void updateProductPrice(Product product, Sale sale, String action) {
        if (action.equals("Start")) {
            BigDecimal originalPrice = product.getPrice();
            if (sale.getSaleInNumber() != null) {
                BigDecimal updatedSalePrice = originalPrice.subtract(sale.getSaleInNumber());
                product.setPrice(updatedSalePrice);
            } else if (sale.getSaleInPercent() != null) {
                BigDecimal salePrice = calculatePercentageOfAmount(originalPrice, sale.getSaleInPercent());
                product.setPrice(salePrice);
            }

            productRepository.save(product);
        } else {
            deleteSale(sale.getId());
            product.setSalePrice(null);
        }
    }

    private BigDecimal calculatePercentageOfAmount(BigDecimal amount, int percentage) {
        if (amount.compareTo(BigDecimal.ZERO) == 0) {
            throw new IllegalArgumentException("Amount cannot be zero.");
        }

        BigDecimal percentageDecimal = BigDecimal.valueOf(100 - percentage);
        BigDecimal result = amount.multiply(percentageDecimal.divide(new BigDecimal("100")));

        return result.setScale(2, BigDecimal.ROUND_HALF_UP);
    }


    private void ss(){
        LocalDateTime startTime = LocalDateTime.now().minusMinutes(10);
        LocalDateTime endTime = LocalDateTime.now().plusMinutes(10);

        List<Sale> allEndingSale = saleRepository.findAllEndingSale(startTime, endTime);
        for (Sale sale : allEndingSale) {
            System.out.println(sale.getCreatedDate());
        }
    }

}
