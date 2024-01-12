package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.model.CategoryModel;
import ge.ecomerce.newecomerce.repository.CategoryRepository;
import ge.ecomerce.newecomerce.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

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
}
