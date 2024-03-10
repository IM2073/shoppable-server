package ecommerce.server.dto;

import ecommerce.server.entity.Product;
import lombok.Data;

@Data
public class CartDto {
    private Integer id;
    private Product product;
    private Integer quantity;
}
