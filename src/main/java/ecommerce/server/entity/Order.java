package ecommerce.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name="orders")
public class Order {
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
