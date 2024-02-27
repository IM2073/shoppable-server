package ecommerce.server.repository;

import ecommerce.server.entity.Cart;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Integer> {
    @Transactional
    @Query(value = "SELECT c.* FROM carts c INNER JOIN products p ON c.product_id = p.id WHERE c.user_id = :userId", nativeQuery = true)
    List<Cart> getUserCart(@Param("userId") Integer userId);
}
