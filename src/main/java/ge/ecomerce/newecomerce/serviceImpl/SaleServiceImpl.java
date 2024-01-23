package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.product.Product;
import ge.ecomerce.newecomerce.entity.product.Sale;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.model.request.SaleModel;
import ge.ecomerce.newecomerce.repository.ProductRepository;
import ge.ecomerce.newecomerce.repository.SaleRepository;
import ge.ecomerce.newecomerce.service.SaleService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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


    @PostConstruct
    public void initialize() {
        // Get the current time
        LocalDateTime now = LocalDateTime.now();

        // Set the desired execution time (e.g., tomorrow at 8:00 AM)
        LocalDateTime executionTime = LocalDateTime.now().plusMinutes(1);

        // Calculate the initial delay in seconds until the desired execution time
        long initialDelay = Duration.between(now, executionTime).getSeconds();

        // Schedule the task for one-time execution
        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.schedule(this::runOneTimeTask, initialDelay, TimeUnit.SECONDS);
    }

    private void runOneTimeTask() {
        // Your one-time task logic here
        System.out.println("execute");

        // Optionally, you can shutdown the executor service after execution
        // executorService.shutdown();
    }
}
