package com.store.api.dto;

import com.store.api.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderDto {
    private Long id;
    private Long customerId;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public OrderDto(
            Long id,
            Long customerId,
            OrderStatus status,
            LocalDateTime createdAt) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.createdAt = createdAt;
    }
}
