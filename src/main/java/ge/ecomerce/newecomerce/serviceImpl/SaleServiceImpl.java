package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.product.Product;
import ge.ecomerce.newecomerce.entity.product.Sale;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.model.request.SaleModel;
import ge.ecomerce.newecomerce.repository.ProductRepository;
import ge.ecomerce.newecomerce.repository.SaleRepository;
import ge.ecomerce.newecomerce.runnable.StartSale;
import ge.ecomerce.newecomerce.service.SaleService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Slf4j
public class SaleServiceImpl implements SaleService {
    private final TaskScheduler taskScheduler;

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;


    @Override
    public Sale addNewSale(SaleModel saleModel) {
        try {
            List<Long> salesId = new ArrayList<>();
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
            salesId.add(savedSale.getId());
            Instant instant = Instant.now().plusSeconds(Duration.between(LocalDateTime.now(), savedSale.getStartDate()).getSeconds());
            taskScheduler.schedule(
                    new StartSale(productRepository, saleRepository, salesId),
                    instant
            );
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
                    productRepository.save(product);
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


//    @Scheduled(fixedRate = 60 * 1000)
//    @Transactional
//    public void endSale() {
//        try {
//            LocalDateTime startTime = LocalDateTime.now().minusMinutes(1);
//            LocalDateTime endTime = LocalDateTime.now().plusMinutes(1);
//
//            List<Sale> allEndingSale = saleRepository.findAllEndingSale(startTime, endTime);
//
//            if (!allEndingSale.isEmpty()) {
//                for (Sale sale : allEndingSale) {
//                    List<Product> products = productRepository.getByProductBySaleId(sale.getId());
//                    if (!products.isEmpty()) {
//                        for (Product product : products) {
//                            updateProductPrice(product, sale, "End");
//                        }
//                    }
//                }
//            }
//        } catch (Exception e) {
//            throw new RuntimeException(e);
//        }
//    }


}
