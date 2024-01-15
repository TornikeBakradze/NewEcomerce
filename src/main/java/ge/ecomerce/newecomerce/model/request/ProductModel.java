package ge.ecomerce.newecomerce.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class ProductModel {

    @NotNull(message = "Product Name and Description not be null")
    private ProductNameAndDescriptionModel productNameAndDescriptionModel;

    @NotNull(message = "subCategoryID must not be null")
    private Long subCategoryID;

}
