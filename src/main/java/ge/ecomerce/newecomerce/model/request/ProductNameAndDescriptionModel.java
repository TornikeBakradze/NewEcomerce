package ge.ecomerce.newecomerce.model.request;

import jakarta.validation.constraints.*;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
public class ProductNameAndDescriptionModel {
    @NotNull(message = "Product name must no be null")
    @NotBlank(message = "Product name must not be blank")
    @Size(min = 2, max = 50, message = "Product name must be 2 to 50")
    @Pattern(regexp = "^[a-zA-Z0-9 -]+$", message = "Product name must contain only letters and number")
    private String name;

    @Size(min = 2, max = 50, message = "Product name must be 2 to 50")
    @Pattern(regexp = "^[a-zA-Z0-9 -]+$", message = "Product name must contain only letters and number")
    private String brand;

    @NotNull(message = "Product price can't be null")
    @Min(value = 1, message = "Price can't be negative number")
    private BigDecimal price;

    @Min(value = 0, message = "Quantity can't be negative number")
    private Integer quantity;

    @Size(max = 3000, message = "the details is to long")
    @Pattern(regexp = "^[a-zA-Z0-9 %+.,!';:-]+$", message = "details can only contains letters numbers and % + , . ! ; ' : - symbol")
    @NotBlank(message = "Description name must not be blank")
    private String details;

    @Size(max = 1000, message = "the description is to long")
    @Pattern(regexp = "^[a-zA-Z0-9 %+.,!';:-]+$", message = "Description can only contains letters numbers and % + , . ! ; ' : - symbol")
    @NotBlank(message = "Description name must not be blank")
    private String description;

}
