package ecommerce.server.repository;

import ecommerce.server.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

    @Transactional
    @Query(value = "SELECT * FROM products p WHERE (:categoryId IS NULL OR p.categoryId = :categoryId)", nativeQuery = true)
    List<Product> getProducts(Integer categoryId);

    @Transactional
    @Query(value = "SELECT * FROM products p WHERE p.id = :productId", nativeQuery = true)
    Product getProductDetail(Integer productId);
}

