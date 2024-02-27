package ecommerce.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

@Entity
@Table(name="orderDetails")
public class OrderDetail {
    @Column(name="id")
    @GeneratedValue
    private Integer id;

    @Column(name="productId")
    private Integer productId;

    @Column(name="orderId")
    private Integer orderId;

    @Column(name="amount")
    private Integer amount;
}
