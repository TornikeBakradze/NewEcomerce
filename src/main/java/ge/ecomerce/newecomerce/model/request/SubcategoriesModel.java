package ge.ecomerce.newecomerce.model.request;

import ge.ecomerce.newecomerce.anotation.ListValidation.ListOfStringAnnotation;
import jakarta.validation.constraints.*;
import lombok.Getter;

import java.util.List;

@Getter
public class SubcategoriesModel {

    @ListOfStringAnnotation
    private List<String> subCategoryName;

    @NotNull(message = "categoryID must not be null")
    private Long categoryID;
}
