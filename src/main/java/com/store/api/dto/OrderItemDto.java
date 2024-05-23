package com.store.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderItemDto {
    private long id;
    private int quantity;
    private long orderId;
    private long productId;
    private LocalDateTime createdAt;

    public OrderItemDto(
            int quantity,
            long orderId,
            long productId
    ) {
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }
}
