package ecommerce.server.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="orders")
@Data
public class Order {
    @Id
    @Column(name="id")
    @GeneratedValue
    private Integer id;

    @Column(name="status")
    private String status;

    @Column(name="user_id")
    private Integer userId;

    @Column(name="total_price")
    private Integer totalPrice;

    @Column(name="date_in")
    private LocalDateTime dateIn;
}
