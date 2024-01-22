package ge.ecomerce.newecomerce.controller;

import ge.ecomerce.newecomerce.entity.product.Sale;
import ge.ecomerce.newecomerce.model.request.SaleModel;
import ge.ecomerce.newecomerce.service.SaleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
}
