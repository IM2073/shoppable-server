package ecommerce.server.controller;

import ecommerce.server.entity.Product;
import ecommerce.server.model.request.ProductRequest;
import ecommerce.server.service.ProductService;
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
@RequestMapping("/product")
@SecurityRequirement(name="Bearer Authentication")
@Slf4j
public class ProductController {
    private final ProductService productService;
    @GetMapping
    public ResponseEntity<List<Product>> getAllProduct(@RequestParam(required = false) String categorySlug, @RequestParam(required = false) String productName) {
        List<Product> productList = productService.getProducts(categorySlug, productName);
        return ResponseEntity.status(HttpStatus.OK).body(productList);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<Product> getProductDetail(@PathVariable Integer productId) {
        Product product = productService.getProductDetail(productId);
        return ResponseEntity.status(HttpStatus.OK).body(product);
    }

    // TODO: admin only to add product
    @PostMapping
    public ResponseEntity<Object> addProduct(@Valid @RequestBody ProductRequest productRequest) {
        log.info(productRequest.toString());
        productService.addProduct(productRequest);
        return ResponseEntity.status(HttpStatus.OK).body("product added successfully");
    }
}
