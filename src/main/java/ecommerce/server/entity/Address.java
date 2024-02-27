package ecommerce.server.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity(name="addresses")
public class Address {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="city")
    private String city;

    @Column(name="state")
    private String state;

    @Column(name="postalCode")
    private String postalCode;

    @Column(name="streetAddress")
    private String streetAddress;

    @Column(name="userId")
    private String userId;
}
