package ge.ecomerce.newecomerce.serviceImpl;

import ge.ecomerce.newecomerce.entity.category.Category;
import ge.ecomerce.newecomerce.repository.CategoryRepository;
import ge.ecomerce.newecomerce.service.CategoryService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public List<Category> getAll() {
        return categoryRepository.findAll();
    }
}
