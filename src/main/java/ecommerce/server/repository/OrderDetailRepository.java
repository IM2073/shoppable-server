package ecommerce.server.repository;

import ecommerce.server.entity.OrderDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    Optional<List<OrderDetail>> findAllByOrderId(Integer orderId);
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO order_details (product_id, order_id, quantity, subtotal) VALUES (:productId, :orderId, :quantity, :subtotal)", nativeQuery = true)
    void addOrderDetail(@Param("productId") Integer productId, @Param("orderId") Integer orderId, @Param("quantity") Integer quantity, @Param("subtotal") Integer subtotal);
}
