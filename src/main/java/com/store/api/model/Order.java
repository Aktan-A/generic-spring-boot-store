package com.store.api.model;

import com.store.api.enums.OrderStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private OrderStatus status;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    private Transaction transaction;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orders = new ArrayList<>();

    public Order() {}

    public Order(OrderStatus status) {
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, status, createdAt);
    }
}
