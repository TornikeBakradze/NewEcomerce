package ge.ecomerce.newecomerce.boostrap;

import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BoostrapData implements CommandLineRunner {

    private final CategoryRepository categoryRepository;

    @Override
    public void run(String... args) throws Exception {
        loadCategory();
    }

    private void loadCategory(){
        if ((categoryRepository.count() == 0)) {
            Category category1 = Category.builder()
                    .name("Houseandgarden")
                    .build();
            Category category2 = Category.builder()
                    .name("homeappliances")
                    .build();
            Category category3 = Category.builder()
                    .name("Technic")
                    .build();

            categoryRepository.save(category1);
            categoryRepository.save(category2);
            categoryRepository.save(category3);
        }
    }
}
