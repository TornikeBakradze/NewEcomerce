package ge.ecomerce.newecomerce.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Getter
public class ProductNameAndDescriptionModel {
    @NotNull(message = "Product name must no be null")
    @NotBlank(message = "Product name must not be blank")
    @Size(min = 2, max = 50, message = "Product name must be 2 to 50")
    @Pattern(regexp = "^[a-zA-Z0-9 ]+$", message = "Product name must contain only letters and number")
    private String name;

    @Size(max = 1000, message = "the description is to long")
    @Pattern(regexp = "^[a-zA-Z0-9 %.,!]+$", message = "Description can only contains letters numbers and % , . ! symbol")
    @NotBlank(message = "Description name must not be blank")
    private String description;
}
