package ecommerce.server.service.Impl;

import ecommerce.server.dto.CustomException;
import ecommerce.server.dto.PaginationProductDto;
import ecommerce.server.entity.Product;
import ecommerce.server.model.request.ProductRequest;
import ecommerce.server.repository.CategoryRepository;
import ecommerce.server.repository.ProductRepository;
import ecommerce.server.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.regex.Pattern;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");
    private static final Pattern WHITESPACE = Pattern.compile("[\\s]");

    @Override
    public PaginationProductDto getProducts(String categorySlug, String productName, Integer currPage) {
        int offset = ((currPage == null ? 1: currPage) - 1) * 12;
        int totalRows = productRepository.getTotalRows(categorySlug);
        int totalPages = (int) Math.ceil((double) totalRows / 12);
        List<Product> productList = productRepository.getProducts(categorySlug, productName, offset);

        return PaginationProductDto.builder()
                .product(productList)
                .totalPages(totalPages)
                .build();
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

    @Override
    public List<Product> getSimilarProducts(Integer productId) {
        Product product = productRepository.getProductDetail(productId).orElseThrow(() -> new CustomException("Product id not found", 404));
        return productRepository.getSimilarProducts(product.getId(), product.getCategoryId());
    }
}
