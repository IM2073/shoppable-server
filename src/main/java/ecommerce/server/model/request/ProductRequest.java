package ecommerce.server.model.request;

import lombok.Data;

@Data
public class ProductRequest {
    private String name;
    private String description;
    private String imageUrl;
    private Integer stock;
    private Integer price;
    private Integer categoryId;
}
