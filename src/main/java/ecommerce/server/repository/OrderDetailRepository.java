package ecommerce.server.repository;

import ecommerce.server.entity.OrderDetail;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Transactional
    @Query(value = "SELECT * FROM orderDetails o WHERE o.order_id = :orderId", nativeQuery = true)
    Optional<List<OrderDetail>> getOrderDetail(@Param("orderId") Integer orderId);

}
