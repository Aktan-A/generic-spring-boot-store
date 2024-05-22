package com.store.api.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.store.api.enums.ProductStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "products")
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
    private ProductStatus status;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    @CreationTimestamp
    @Column(nullable = false)
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "product")
    List<OrderItem> orderItems = new ArrayList<>();

    public Product() {}

    public Product(String name,
                   ProductStatus status,
                   String description,
                   Double price) {
        this.name = name;
        this.status = status;
        this.description = description;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", status=" + status +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, status, description, price, createdAt);
    }
}
