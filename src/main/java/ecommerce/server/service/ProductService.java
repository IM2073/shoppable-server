package ecommerce.server.service;

import ecommerce.server.entity.Product;

import java.util.List;

public interface ProductService {
    List<Product> getProducts(Integer categoryId);
    Product getProductDetail(Integer productId);
}
