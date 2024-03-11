package ecommerce.server.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CustomException extends RuntimeException {
    private String message;
    private int status;
}
