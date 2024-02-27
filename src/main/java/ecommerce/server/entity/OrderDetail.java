package ecommerce.server.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="orderDetails")
@Data
public class OrderDetail {
    @Id
    @Column(name="id")
    @GeneratedValue
    private Integer id;

    @Column(name="product_id")
    private Integer productId;

    @Column(name="order_id")
    private Integer orderId;

    @Column(name="amount")
    private Integer amount;
}
