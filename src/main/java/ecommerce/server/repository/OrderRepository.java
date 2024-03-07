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
    @Query(value = "UPDATE orders o SET status = 'paid' WHERE id = :orderId AND user_id = :userId", nativeQuery = true)
    void updateOrderStatus(@Param("orderId") Integer orderId, @Param("userId") Integer userId);

}
