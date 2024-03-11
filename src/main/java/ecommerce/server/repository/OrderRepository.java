package ecommerce.server.repository;

import ecommerce.server.entity.Order;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Integer> {
    @Transactional
    @Query(value = "SELECT * FROM orders o WHERE o.user_id = :userId", nativeQuery = true)
    List<Order> getUserOrders(@Param("userId") Integer userId);

    @Transactional
    @Query(value = "SELECT * FROM orders o WHERE id = :orderId", nativeQuery = true)
    Optional<Order> getOrderById(@Param("orderId") Integer orderId);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO orders (user_id, total_price, status) VALUES (:userId, :totalPrice, :status)", nativeQuery = true)
    void addOrder(@Param("userId") Integer userId, @Param("totalPrice") Integer totalPrice, @Param("status") String status);

    @Query(value = "SELECT id FROM orders WHERE user_id = :userId ORDER BY id DESC LIMIT 1", nativeQuery = true)
    Integer getLastInsertedOrderIdForUser(@Param("userId") Integer userId);
}
