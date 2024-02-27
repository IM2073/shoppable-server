package ecommerce.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="carts")
public class Cart {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="userId")
    private Integer userId;

    @Column(name="productId")
    private Integer productId;

    @Column(name="quantity")
    private Integer quantity;
}
