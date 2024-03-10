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
    @Query(value = "SELECT p.id, p.name, p.description, p.image_url, p.stock, p.price, p.category_id FROM products p JOIN categories c ON (c.id = p.category_id) WHERE (:categorySlug IS NULL OR LOWER(c.slug) LIKE LOWER(concat('%', :categorySlug, '%'))) AND (:productName IS NULL OR LOWER(p.name) LIKE LOWER(concat('%', :productName, '%'))) AND stock > 0 LIMIT 12 OFFSET :offset", nativeQuery = true)
    List<Product> getProducts(@Param("categorySlug") String categorySlug, @Param("productName") String productName, @Param("offset") int offset);

    @Transactional
    @Query(value = "SELECT COUNT(*) FROM products p JOIN categories c ON (c.id = p.category_id) WHERE (:categorySlug IS NULL OR LOWER(c.slug) LIKE LOWER(concat('%', :categorySlug, '%')));", nativeQuery = true)
    Integer getTotalRows(@Param("categorySlug") String categorySlug);

    @Transactional
    @Modifying
    @Query(value = "UPDATE products p SET p.stock = p.stock - :quantity WHERE p.id = :productId", nativeQuery = true)
    void consumeProductStock(@Param("productId") Integer productId, @Param("quantity") Integer quantity);

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
                    @Param("categoryId") Integer categoryId
                    );

}

