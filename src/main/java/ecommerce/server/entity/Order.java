package ecommerce.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.UUID;

@Entity
@Table(name="Orders")
public class Order {
    @Column(name="id")
    private UUID orderId;

    @Column(name="totalPrice")
    private UUID totalPrice;
}
