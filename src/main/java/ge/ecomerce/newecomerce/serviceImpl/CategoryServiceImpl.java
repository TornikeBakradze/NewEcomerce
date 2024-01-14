package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.model.request.CategoryModel;
import ge.ecomerce.newecomerce.model.respone.CategoryWithSubcategories;
import ge.ecomerce.newecomerce.repository.CategoryRepository;
import ge.ecomerce.newecomerce.repository.SubcategoryRepository;
import ge.ecomerce.newecomerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Override
    public List<Category> getAll() {
        try {

            List<Category> categoryList = categoryRepository.findAll();
            if (categoryList.isEmpty()) {
                throw new DataNotFoundException("Category not found");
            } else
                return categoryList;
        } catch (DataNotFoundException e) {
            throw new DataNotFoundException(e.getMessage());
        }
    }

    @Override
    public Category getById(Long id) {
        return categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("Category not found"));
    }

    @Override
    public Category updateCategory(Long categoryId, CategoryModel categoryModel) {
        try {

            Category category =
                    categoryRepository.findById(categoryId).orElseThrow(() -> new DataNotFoundException("Category not found"));

            category.setName(categoryModel.getName());

            return categoryRepository.save(category);

        } catch (DataNotFoundException e) {
            throw new DataNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public Category saveCategory(CategoryModel categoryModel) {
        try {
            Category category = Category.builder()
                    .name(categoryModel.getName())
                    .build();
            return categoryRepository.save(category);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String delete(Long id) {
        try {
            Category category = categoryRepository.findById(id).orElseThrow(() -> new DataNotFoundException("No category found"));
            categoryRepository.delete(category);
            return "Category deleted successfully";
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public CategoryWithSubcategories categoryWithSubcategoryByCategoryID(Long categoryID) {
        try {
            Category category = categoryRepository.findById(categoryID).
                    orElseThrow(() -> new DataNotFoundException("No category found"));
            List<String> subCategories = subcategoryRepository.getCategoryWithSubCategoryByID(categoryID);
            if (subCategories.isEmpty()) {
                throw new DataNotFoundException(String.format("%s subcategories not found", category.getName()));
            }
            return new CategoryWithSubcategories(category.getName(), subCategories);
        } catch (DataNotFoundException e) {
            throw new DataNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<CategoryWithSubcategories> categoryWithSubcategoryByCategories() {
        try {
            List<CategoryWithSubcategories> categoryWithSubcategoriesList = new ArrayList<>();
            List<Category> categoryList = categoryRepository.findAll();
            if (categoryList.isEmpty())
                throw new DataNotFoundException("No category found");
            for (Category category : categoryList) {
                List<String> subCategories =
                        subcategoryRepository.getCategoryWithSubCategoryByID(category.getId());

                CategoryWithSubcategories categoryWithSubcategories =
                        new CategoryWithSubcategories(category.getName(), subCategories);
                categoryWithSubcategoriesList.add(categoryWithSubcategories);
            }
            return categoryWithSubcategoriesList;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
