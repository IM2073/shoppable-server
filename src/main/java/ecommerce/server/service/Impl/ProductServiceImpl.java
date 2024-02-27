package ecommerce.server.service.Impl;

import ecommerce.server.entity.Product;
import ecommerce.server.repository.ProductRepository;
import ecommerce.server.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    @Override
    public List<Product> getProducts(Integer categoryId) {
        return productRepository.getProducts(categoryId);
    }

    @Override
    public Product getProductDetail(Integer productId) {
        return productRepository.getProductDetail(productId);
    }
}
