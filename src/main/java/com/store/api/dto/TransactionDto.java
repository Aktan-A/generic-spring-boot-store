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
    private long id;
    private double totalPrice;
    private TransactionStatus status;
    private LocalDateTime createdAt;

    public TransactionDto(
            long id,
            double totalPrice,
            TransactionStatus status,
            LocalDateTime createdAt) {
        this.id = id;
        this.totalPrice = totalPrice;
        this.status = status;
        this.createdAt = createdAt;
    }
}
