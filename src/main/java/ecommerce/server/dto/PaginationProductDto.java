package ecommerce.server.dto;

import ecommerce.server.entity.Product;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PaginationProductDto {
    private List<Product> product;
    private Integer totalPages;

}
