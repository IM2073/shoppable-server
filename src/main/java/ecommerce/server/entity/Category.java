package ecommerce.server.entity;

import jakarta.persistence.*;

import java.util.UUID;

@Entity
@Table(name="categories")
public class Category {
    @Id
    @GeneratedValue
    @Column(name="id")
    private Integer id;

    @Column(name="name")
    private String name;
}
