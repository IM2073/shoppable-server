package ecommerce.server.entity;

import jakarta.persistence.*;

@Entity
@Table(name="orderDetails")
public class OrderDetail {
    @Id
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
