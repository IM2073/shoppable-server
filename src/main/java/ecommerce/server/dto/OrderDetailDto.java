package ecommerce.server.dto;

import ecommerce.server.entity.Order;
import ecommerce.server.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderDetailDto {
    private Integer id;
    private Product productOrder;
    private Order order;
    private Integer quantity;
    private Integer subtotal;
    private LocalDateTime dateIn;
}
