package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.entity.category.Subcategory;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.model.request.SubcategoryModel;
import ge.ecomerce.newecomerce.repository.CategoryRepository;
import ge.ecomerce.newecomerce.repository.SubcategoryRepository;
import ge.ecomerce.newecomerce.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class SubCategoryServiceImpl implements SubCategoryService {

    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Override
    public List<Subcategory> getAll() {
        try {
            List<Subcategory> allSubCategory = subcategoryRepository.findAll();
            if (!allSubCategory.isEmpty()) {
                return subcategoryRepository.findAll();
            } else {
                throw new DataNotFoundException("No subcategory find");
            }
        } catch (DataNotFoundException e) {
            throw new DataNotFoundException(e.getMessage());
        }
    }

    @Override
    public Subcategory getSubCategoryByName(String name) {
        return subcategoryRepository.findByName(name).
                orElseThrow(() -> new DataNotFoundException(String.format("%s category not found", name)));
    }

    @Override
    public String saveSubCategory(SubcategoryModel subcategoryModel) {
        try {
            Optional<Category> category = categoryRepository.findById(subcategoryModel.getCategoryID());
            if (category.isEmpty()) {
                throw new DataNotFoundException("This category not found");
            }
            Subcategory subcategory = Subcategory.builder()
                    .name(subcategoryModel.getSubCategoryName())
                    .category(category.get())
                    .build();
            subcategoryRepository.save(subcategory);
            return "Sub Category added successfully";
        } catch (DataIntegrityViolationException ex) {
            throw new DataIntegrityViolationException("Not correct field.");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteSubcategoryByID(Long id) {
        try {
            Subcategory subcategory =
                    subcategoryRepository.findById(id).orElseThrow(() ->
                            new DataNotFoundException(String.format("Category with %s id not found", id)));
            subcategoryRepository.delete(subcategory);
            return String.format("Category with %s id deleted successfully", id);
        } catch (DataNotFoundException e) {
            throw new DataNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Subcategory updateCategory(Long id, SubcategoryModel subcategoryModel) {
        try {
            Subcategory subcategory =
                    subcategoryRepository.findById(id).orElseThrow(() ->
                            new DataNotFoundException(String.format("Category with %s id not found", id)));
            Category category =
                    categoryRepository.findById(subcategoryModel.getCategoryID()).orElseThrow(() ->
                            new DataNotFoundException(String.format("Category with %s id not found", subcategoryModel.getCategoryID())));
            subcategory.setName(subcategoryModel.getSubCategoryName());
            subcategory.setCategory(category);
            subcategoryRepository.save(subcategory);
            return subcategory;
        } catch (DataNotFoundException e) {
            throw new DataNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
