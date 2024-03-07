package ecommerce.server.entity;

import jakarta.persistence.*;
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(name="quantity")
    private Integer quantity;
}
