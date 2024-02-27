package ecommerce.server.service;


import ecommerce.server.entity.Category;
import ecommerce.server.model.request.CategoryRequest;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategories();
    void addCategory(CategoryRequest categoryRequest);
}
