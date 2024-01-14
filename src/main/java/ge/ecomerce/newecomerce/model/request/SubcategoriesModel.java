package ge.ecomerce.newecomerce.model.request;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

import java.util.List;

@Getter
public class SubcategoriesModel {

    @NotEmpty(message = "SubCategories list must no be empty")
    private List<String> subCategoryName;

    @NotNull(message = "categoryID must not be null")
    private Long categoryID;
}
