package com.store.api.model;

import com.store.api.enums.TransactionStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "transactions")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"order"})
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "total_price", nullable = false)
    private double totalPrice;
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private TransactionStatus status = TransactionStatus.CREATED;
    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;

    @OneToOne(mappedBy = "transaction", optional = false)
    private Order order;

    public Transaction(double totalPrice, TransactionStatus status) {
        this.totalPrice = totalPrice;
        this.status = status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalPrice, status, createdAt);
    }
}
