package ecommerce.server.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CategoryRequest {
    @NotBlank(message = "Category name is mandatory")
    @NotNull(message = "Category name is mandatory")
    private String name;
}
