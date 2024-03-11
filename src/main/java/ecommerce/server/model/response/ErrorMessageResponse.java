package ecommerce.server.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessageResponse {
    private String message;
}
