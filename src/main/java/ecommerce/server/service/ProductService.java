package ecommerce.server.service;

import ecommerce.server.dto.PaginationProductDto;
import ecommerce.server.entity.Product;
import ecommerce.server.model.request.ProductRequest;

import java.util.List;

public interface ProductService {
    PaginationProductDto getProducts(String categorySlug, String productName, Integer currPage);
    Product getProductDetail(Integer productId);
    void addProduct(ProductRequest productRequest);
}
