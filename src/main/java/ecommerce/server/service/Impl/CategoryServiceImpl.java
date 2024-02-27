package ecommerce.server.service.Impl;

import ecommerce.server.entity.Category;
import ecommerce.server.model.request.CategoryRequest;
import ecommerce.server.repository.CategoryRepository;
import ecommerce.server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.getAllCategories();
    }

    @Override
    public void addCategory(CategoryRequest categoryRequest) {
        categoryRepository.addCategory(categoryRequest.getName());
    }
}
