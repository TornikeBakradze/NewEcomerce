package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.entity.category.Subcategory;
import ge.ecomerce.newecomerce.model.SubcategoryModel;
import ge.ecomerce.newecomerce.repository.CategoryRepository;
import ge.ecomerce.newecomerce.repository.SubcategoryRepository;
import ge.ecomerce.newecomerce.service.SubCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SubCategoryServiceImpl implements SubCategoryService {

    private final CategoryRepository categoryRepository;
    private final SubcategoryRepository subcategoryRepository;

    @Override
    public List<Subcategory> getAll() {
        List<Subcategory> all = subcategoryRepository.findAll();
        return all;
    }

    @Override
    @Transactional
    public String saveSubCategory(SubcategoryModel subcategoryModel) {
        Category category = categoryRepository.findAll().get(Math.toIntExact(subcategoryModel.getCategoryID()));

        Subcategory subcategory=Subcategory.builder()
                .name(subcategoryModel.getSubCategoryName())
                .category(category)
                .build();

        subcategoryRepository.save(subcategory);
        return "Sub Category added successfully";
    }
}
