package com.store.api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.store.api.enums.ProductStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"orderItems"})
public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProductStatus status = ProductStatus.ACTIVE;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private Double price;

    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "product")
    List<OrderItem> orderItems = new ArrayList<>();

    public Product(String name,
                   ProductStatus status,
                   String description,
                   Double price) {
        this.name = name;
        this.status = status;
        this.description = description;
        this.price = price;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, description, price, createdAt);
    }
}
