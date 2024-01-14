package ge.ecomerce.newecomerce.model.request;


import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;

import java.util.List;

@Getter
public class CategoriesModel {
    @NotEmpty(message = "Categories list must no be empty")
    private List<String> categoriesName;
}
