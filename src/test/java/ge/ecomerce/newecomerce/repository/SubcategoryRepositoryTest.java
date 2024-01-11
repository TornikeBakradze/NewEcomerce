package ge.ecomerce.newecomerce.repository;

import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.entity.category.Subcategory;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class SubcategoryRepositoryTest {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubcategoryRepository subcategoryRepository;

    Category testCategory;

    @BeforeEach
    void setUp() {
        testCategory = categoryRepository.findAll().get(0);
    }

    @Test
    @Transactional
    void subCategoryRepositoryTest() {
        Subcategory subcategory = Subcategory.builder()
                .name("House clining")
                .category(testCategory)
                .build();

        Subcategory saveSubCategory = subcategoryRepository.saveAndFlush(subcategory);

        List<Subcategory> all = subcategoryRepository.findAll();
        System.out.println("y");
    }
}