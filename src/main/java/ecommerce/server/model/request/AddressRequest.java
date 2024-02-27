package ecommerce.server.model.request;

import lombok.Data;

@Data
public class AddressRequest {
    private String city;
    private String state;
    private String postalCode;
    private String streetAddress;
}
