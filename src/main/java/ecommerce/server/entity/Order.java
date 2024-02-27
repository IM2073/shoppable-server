package ecommerce.server.entity;

import jakarta.persistence.*;
@Entity
@Table(name="orders")
public class Order {
    @Id
    @Column(name="id")
    @GeneratedValue
    private Integer id;

    @Column(name="addressId")
    private Integer addressId;

    @Column(name="status")
    private String status;

    @Column(name="userId")
    private Integer userId;

    @Column(name="totalPrice")
    private Integer totalPrice;

}
