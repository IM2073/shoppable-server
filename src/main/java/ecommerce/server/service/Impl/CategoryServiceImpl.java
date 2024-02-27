package ecommerce.server.service.Impl;

import ecommerce.server.entity.Category;
import ecommerce.server.model.request.CategoryRequest;
import ecommerce.server.repository.CategoryRepository;
import ecommerce.server.service.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
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
