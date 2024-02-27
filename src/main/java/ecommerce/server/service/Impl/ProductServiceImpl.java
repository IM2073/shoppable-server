package ecommerce.server.service.Impl;

import ecommerce.server.dto.CustomException;
import ecommerce.server.entity.Product;
import ecommerce.server.model.request.ProductRequest;
import ecommerce.server.repository.CategoryRepository;
import ecommerce.server.repository.ProductRepository;
import ecommerce.server.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Override
    public List<Product> getProducts(Integer categoryId) {
        return productRepository.getProducts(categoryId);
    }

    @Override
    public Product getProductDetail(Integer productId) {
        return productRepository.getProductDetail(productId)
                .orElseThrow(() -> new CustomException("Product not found", 404));
    }

    @Override
    public void addProduct(ProductRequest productRequest) {
        categoryRepository.getCategoryById(productRequest.getCategoryId())
                .orElseThrow(() -> new CustomException("Invalid categoryId", 400));

        productRepository.addProduct(
                productRequest.getName(),
                productRequest.getDescription(),
                productRequest.getImageUrl(),
                productRequest.getStock(),
                productRequest.getPrice(),
                productRequest.getCategoryId()
        );
    }
}
