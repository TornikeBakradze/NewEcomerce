package ge.ecomerce.newecomerce.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;

@Getter
public class CategoryModel {
    @NotBlank(message = "category name must not be blank")
    @NotNull(message = "category name must not be null")
    private String name;
}
