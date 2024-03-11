package ecommerce.server.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CheckoutItemRequest {
    private String productName;
    private int quantity;
    private double price;
    private int productId;
}
