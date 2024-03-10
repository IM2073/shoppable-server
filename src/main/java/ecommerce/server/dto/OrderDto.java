package ecommerce.server.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class OrderDto {
    private Integer id;
    private String status;
    private Integer totalPrice;
    private LocalDateTime dateIn;
    private String imageUrl;
}
