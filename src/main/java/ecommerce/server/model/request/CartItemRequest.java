package ecommerce.server.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CartItemRequest {
    @NotNull(message = "Quantity is mandatory")
    private Integer quantity;
}
