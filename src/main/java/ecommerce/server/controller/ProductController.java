package ecommerce.server.controller;

import ecommerce.server.entity.Category;
import ecommerce.server.entity.Product;
import ecommerce.server.service.ProductService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
@SecurityRequirement(name="Bearer Authentication")
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(@RequestParam(required = false) Integer categoryId) {
        List<Product> productList = productService.getProducts(categoryId);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductDetail(@PathVariable Integer productId) {
        Product product = productService.getProductDetail(productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }
}
