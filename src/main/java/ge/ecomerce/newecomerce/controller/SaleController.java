package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.entity.product.Sale;
import ge.ecomerce.newecomerce.model.request.SaleModel;
import ge.ecomerce.newecomerce.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SaleController {

    private static final String SALE_BASE_URL = "/sale";
    private final SaleService saleService;

    @PostMapping(SALE_BASE_URL + "/addNewSale")
    private ResponseEntity<Sale> addNewSal(@RequestBody @Validated SaleModel saleModel) {
        return new ResponseEntity<>(saleService.addNewSale(saleModel), HttpStatus.OK);
    }

    @GetMapping(SALE_BASE_URL + "/getAll")
    private ResponseEntity<List<Sale>> getAll() {
        return new ResponseEntity<>(saleService.getALLSale(), HttpStatus.OK);
    }

    @GetMapping(SALE_BASE_URL + "/getById/{saleId}")
    private ResponseEntity<Sale> getByID(@RequestParam("saleId") Long saleId) {
        return new ResponseEntity<>(saleService.getByID(saleId), HttpStatus.OK);
    }

    @PutMapping(SALE_BASE_URL + "/updateSale/{saleId}")
    private ResponseEntity<Sale> update(@RequestParam("saleId") Long saleId,
                                        @RequestBody @Validated SaleModel saleModel) {
        return new ResponseEntity<>(saleService.updateSale(saleId, saleModel), HttpStatus.OK);
    }

    @DeleteMapping(SALE_BASE_URL + "/deleteSale/{saleId}")
    private ResponseEntity<String> delete(@RequestParam("saleId") Long saleId) {
        return new ResponseEntity<>(saleService.deleteSale(saleId), HttpStatus.OK);
    }
}
