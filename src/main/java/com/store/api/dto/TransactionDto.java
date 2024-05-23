package com.store.api.dto;

import com.store.api.enums.TransactionStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class TransactionDto {
    private Long id;
    private double totalPrice;
    private Long orderId;
    private TransactionStatus status;
    private LocalDateTime createdAt;

    public TransactionDto(
            Long id,
            double totalPrice,
            Long orderId,
            TransactionStatus status,
            LocalDateTime createdAt) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.orderId = orderId;
        this.status = status;
        this.createdAt = createdAt;
    }
}
