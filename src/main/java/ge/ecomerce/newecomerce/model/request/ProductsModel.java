package ge.ecomerce.newecomerce.model.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class ProductsModel {

    @Valid
    @NotEmpty(message = "Product Name And DescriptionModel must no be empty")
    List<ProductNameAndDescriptionModel> productNameAndDescriptionModelList;

    @NotNull(message = "subCategoryID must not be null")
    private Long subCategoryID;
}
