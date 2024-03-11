package ecommerce.server.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDataDto {
    String token;
    String username;
    String email;
}
