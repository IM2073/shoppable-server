package ecommerce.server.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="products")
public class Product {
    @Id
    @GeneratedValue
    @Column(name="id")
    private UUID id;
    @Column(name="description")
    private String description;
    @Column(name="image")
    private String image;
    @Column(name="stock")
    private Integer stock;
    @Column(name="price")
    private Integer price;
    @Column(name="categoryId")
    private UUID categoryId;
}
