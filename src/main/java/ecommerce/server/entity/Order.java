package ecommerce.server.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name="orders")
@Data
public class Order {
    @Id
    @Column(name="id")
    @GeneratedValue
    private Integer id;

    @Column(name="address_id")
    private Integer addressId;

    @Column(name="status")
    private String status;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="total_price")
    private Integer totalPrice;

}
