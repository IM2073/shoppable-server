package ecommerce.server.repository;

import ecommerce.server.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Transactional
    @Query(value = "SELECT * FROM products WHERE (:categoryId IS NULL OR category_id = :categoryId) AND (:productName IS NULL OR LOWER(name) LIKE LOWER(concat('%', :productName, '%')))", nativeQuery = true)
    List<Product> getProducts(@Param("categoryId") Integer categoryId, @Param("productName") String productName);

    @Transactional
    @Query(value = "SELECT * FROM products p WHERE p.id = :productId", nativeQuery = true)
    Optional<Product> getProductDetail(@Param("productId") Integer productId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO products (name, description, image_url, stock, price, category_id) " +
            "VALUES (:name, :description, :imageUrl, :stock, :price, :categoryId)", nativeQuery = true)
    void addProduct(@Param("name") String name,
                    @Param("description") String description,
                    @Param("imageUrl") String imageUrl,
                    @Param("stock") int stock,
                    @Param("price") double price,
                    @Param("categoryId") Integer categoryId);
}

