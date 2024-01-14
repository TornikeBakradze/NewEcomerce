package ge.ecomerce.newecomerce.model.respone;

import lombok.Getter;

import java.util.List;
@Getter
public class CategoryWithSubcategories {
    private final String categoryName;
    private final List<String> subcategories;

    public CategoryWithSubcategories(String categoryName, List<String> subcategories) {
        this.categoryName = categoryName;
        this.subcategories = subcategories;
    }


}