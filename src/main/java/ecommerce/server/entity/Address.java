package ecommerce.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Entity(name="addresses")
@Data
public class Address {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="postal_code")
    private String postalCode;

    @Column(name="street_address")
    private String streetAddress;

    @Column(name="user_id")
    private String userId;
}
