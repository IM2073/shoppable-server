package ecommerce.server.service;

import ecommerce.server.entity.Product;
import ecommerce.server.model.request.ProductRequest;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(String categorySlug, String productName);
    Product getProductDetail(Integer productId);
    void addProduct(ProductRequest productRequest);
}
