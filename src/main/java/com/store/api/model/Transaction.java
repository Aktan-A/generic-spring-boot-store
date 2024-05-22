package com.store.api.model;

import com.store.api.enums.TransactionStatus;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "total_price", nullable = false)
    private double totalPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "transaction", optional = false)
    private Order order;


    public Transaction() {}

    public Transaction(double totalPrice, TransactionStatus status) {
        this.totalPrice = totalPrice;
        this.status = status;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public TransactionStatus getStatus() {
        return status;
    }

    public void setStatus(TransactionStatus status) {
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
        return "Transaction{" +
                "id=" + id +
                ", totalPrice=" + totalPrice +
                ", status=" + status +
                ", createdAt=" + createdAt +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, status, createdAt);
    }
}
