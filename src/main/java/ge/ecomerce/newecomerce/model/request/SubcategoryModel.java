package ge.ecomerce.newecomerce.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class SubcategoryModel {

    @NotBlank(message = "subcategory name must not be blank")
    @NotNull(message = "subcategory name must not be null")
    private String subCategoryName;

    @NotNull(message = "categoryID must not be null")
    private Long categoryID;
}
