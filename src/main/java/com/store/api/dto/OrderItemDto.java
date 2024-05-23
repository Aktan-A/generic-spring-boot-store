package com.store.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderItemDto {
    private Long id;
    private int quantity;
    private Long orderId;
    private Long productId;
    private LocalDateTime createdAt;

    public OrderItemDto(
            int quantity,
            Long orderId,
            Long productId
    ) {
        this.quantity = quantity;
        this.orderId = orderId;
        this.productId = productId;
    }
}
