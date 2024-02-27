package ecommerce.server.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;

@Entity
@Table(name="products")
@Data
public class Product {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;

    @Column(name="description")
    private String description;

    @Column(name="image")
    private String imageUrl;

    @Column(name="stock")
    private Integer stock;

    @Column(name="price")
    private Integer price;

    @Column(name="category_id")
    private Integer categoryId;
}
