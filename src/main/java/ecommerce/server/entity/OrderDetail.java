package ecommerce.server.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name="order_details")
@Data
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="product_id")
    private Product productOrder;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="order_id")
    private Order order;

    @Column(name="quantity")
    private Integer quantity;

    @Column(name="subtotal")
    private Integer subtotal;

    @Column(name="date_in")
    private LocalDateTime dateIn;
}
