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
    private OrderStatus status;
    private LocalDateTime createdAt;

    public OrderDto(long id, OrderStatus status, LocalDateTime createdAt) {
        this.id = id;
        this.status = status;
        this.createdAt = createdAt;
    }
}
