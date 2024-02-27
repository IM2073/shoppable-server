package ecommerce.server.controller;

import ecommerce.server.entity.Category;
import ecommerce.server.model.request.CategoryRequest;
import ecommerce.server.service.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/category")
@SecurityRequirement(name="Bearer Authentication")
@Slf4j
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping
    public ResponseEntity<List<Category>> getAllCategories() {
        List<Category> categoryList = categoryService.getAllCategories();
        return ResponseEntity.status(HttpStatus.OK).body(categoryList);
    }

    // TODO: only admin can do this, and need authentication
    @PostMapping
    public ResponseEntity<Object> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        log.info(String.valueOf(categoryRequest));
        categoryService.addCategory(categoryRequest);
        return ResponseEntity.status(HttpStatus.OK).body("Categories added successfully");
    }
}
