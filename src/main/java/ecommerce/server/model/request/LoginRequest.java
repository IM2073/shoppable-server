package ecommerce.server.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class LoginRequest {
    @NotBlank(message = "Email is mandatory")
    @NotNull(message = "Email is mandatory")
    private String email;

    @NotBlank(message = "Password is mandatory")
    @NotNull(message = "Password is mandatory")
    private String password;
}
