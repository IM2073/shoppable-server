package ecommerce.server.controller;

import ecommerce.server.entity.Category;
import ecommerce.server.model.request.CategoryRequest;
import ecommerce.server.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/categories")
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categoryList);
    }

    // TODO: only admin can do this, and need authentication
    @PostMapping
    public ResponseEntity<Object> addCategory(@Valid CategoryRequest categoryRequest) {
        categoryService.addCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Categories added successfully");
    }
}
