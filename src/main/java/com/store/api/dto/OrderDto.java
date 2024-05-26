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
    private Long userId;
    private OrderStatus status;
    private LocalDateTime createdAt;

    public OrderDto(
            Long id,
            Long userId,
            OrderStatus status,
            LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.status = status;
        this.createdAt = createdAt;
    }
}
