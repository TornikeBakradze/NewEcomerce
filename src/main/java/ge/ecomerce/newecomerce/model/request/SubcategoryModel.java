package ge.ecomerce.newecomerce.model.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

@Getter
public class SubcategoryModel {

    @NotBlank(message = "subcategory name must not be blank")
    @NotNull(message = "subcategory name must not be null")
    @Size(max = 50, message = "Subcategory name size must be 2 to 50")
    @Pattern(regexp = "^[a-zA-Z0-9 -]*$", message = "Product name must contain only letters and number")
    private String subCategoryName;

    @NotNull(message = "categoryID must not be null")
    private Long categoryID;
}
