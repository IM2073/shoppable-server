package ecommerce.server.repository;

import ecommerce.server.entity.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Transactional
    @Query(value = "SELECT c.* FROM carts c INNER JOIN products p ON c.product_id = p.id WHERE c.user_id = :userId", nativeQuery = true)
    List<Cart> getUserCart(@Param("userId") Integer userId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO carts (user_id, product_id, quantity) VALUES (:userId, :productId, :quantity)", nativeQuery = true)
    void addCartItem(@Param("productId") Integer productId, @Param("userId") Integer userId, @Param("quantity") Integer quantity);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM carts WHERE user_id = :userId and product_id = :productId", nativeQuery = true)
    void deleteCartItem(@Param("productId") Integer productId, @Param("userId") Integer userId);

    @Transactional
    @Query(value = "SELECT * FROM carts c WHERE c.user_id = :userId and c.product_id = :productId", nativeQuery = true)
    Optional<Cart> getCartByUserAndProduct(@Param("productId") Integer productId, @Param("userId") Integer userId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE carts SET quantity = :quantity WHERE id = :cartId", nativeQuery = true)
    void updateQuantity(@Param("cartId") Integer cartId, @Param("quantity") Integer quantity);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM carts WHERE user_id = :userId", nativeQuery = true)
    void deleteUserCart(@Param("userId") Integer userId);
}
