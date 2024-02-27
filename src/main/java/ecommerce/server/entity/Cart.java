package ecommerce.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="carts")
@Data
public class Cart {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="product_id")
    private Integer productId;

    @Column(name="quantity")
    private Integer quantity;
}
