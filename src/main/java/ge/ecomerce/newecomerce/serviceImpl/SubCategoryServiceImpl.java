package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.entity.category.Subcategory;
import ge.ecomerce.newecomerce.exception.DataNotFoundException;
import ge.ecomerce.newecomerce.model.request.SubcategoriesModel;
import ge.ecomerce.newecomerce.model.request.SubcategoryModel;
import ge.ecomerce.newecomerce.repository.CategoryRepository;
import ge.ecomerce.newecomerce.repository.SubcategoryRepository;
import ge.ecomerce.newecomerce.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
    public Subcategory getSubCategoryById(Long id) {
        return subcategoryRepository.findById(id).
                orElseThrow(() -> new DataNotFoundException("Category not found"));
    }

    @Override
    public Subcategory saveSubCategory(SubcategoryModel subcategoryModel) {
        try {
            Optional<Category> category = categoryRepository.findById(subcategoryModel.getCategoryID());
            if (category.isEmpty()) {
                throw new DataNotFoundException("Category not found");
            }
            Subcategory subcategory = Subcategory.builder()
                    .name(subcategoryModel.getSubCategoryName())
                    .category(category.get())
                    .build();
            return subcategoryRepository.save(subcategory);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException
                    (String.format("SubCategory with name %s already exist", subcategoryModel.getSubCategoryName()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Subcategory> saveSubCategories(SubcategoriesModel subcategoriesModel) {
        List<Subcategory> subcategoryList = new ArrayList<>();
        String globalsubCategoryName = null;
        try {
            Category category =
                    categoryRepository.findById(subcategoriesModel.getCategoryID())
                            .orElseThrow(() -> new DataNotFoundException("Category not found"));
            for (String subcategoryName : subcategoriesModel.getSubCategoryName()) {
                globalsubCategoryName = subcategoryName;
                Subcategory subcategory = Subcategory.builder()
                        .name(subcategoryName)
                        .category(category)
                        .build();
                subcategoryList.add(subcategoryRepository.save(subcategory));
            }
            return subcategoryList;
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityViolationException
                    (String.format("SubCategory with name %s already exist", globalsubCategoryName));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Subcategory updateCategory(Long id, SubcategoryModel subcategoryModel) {
        try {
            Subcategory subcategory =
                    subcategoryRepository.findById(id).orElseThrow(() ->
                            new DataNotFoundException("SubCategory  not found"));
            Category category =
                    categoryRepository.findById(subcategoryModel.getCategoryID()).orElseThrow(() ->
                            new DataNotFoundException("Category not found"));
            subcategory.setName(subcategoryModel.getSubCategoryName());
            subcategory.setCategory(category);
            return subcategoryRepository.save(subcategory);
        } catch (DataNotFoundException e) {
            throw new DataNotFoundException(e.getMessage());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteSubcategoryByID(Long id) {
        try {
            Subcategory subcategory =
                    subcategoryRepository.findById(id).orElseThrow(() ->
                            new DataNotFoundException("SubCategory not found"));
            subcategoryRepository.delete(subcategory);
            return String.format("SubCategory with %s id deleted successfully", id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String deleteAllByCategoryID(Long categoryID) {
        try {
            Category category =
                    categoryRepository.findById(categoryID).orElseThrow(() ->
                            new DataNotFoundException("Category  not found"));
            subcategoryRepository.deleteByCategoryId(category.getId());
            return String.format("Deleted all Subcategory of %s", category.getName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
