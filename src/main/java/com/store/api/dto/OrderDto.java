package com.store.api.dto;

import com.store.api.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderDto {
    private long id;
    private long customerId;
    private long transactionId;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public OrderDto(
            long id,
            long customerId,
            long transactionId,
            OrderStatus status,
            LocalDateTime createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.transactionId = transactionId;
        this.status = status;
        this.createdAt = createdAt;
    }
}
