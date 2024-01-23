package ge.ecomerce.newecomerce.service;

import ge.ecomerce.newecomerce.entity.product.Sale;
import ge.ecomerce.newecomerce.model.request.SaleModel;

import java.util.List;

public interface SaleService {
    Sale addNewSale(SaleModel saleModel);

    List<Sale> getALLSale();

    Sale getByID(Long saleID);

    Sale updateSale(Long saleID, SaleModel saleModel);

    String deleteSale(Long saleID);
}
