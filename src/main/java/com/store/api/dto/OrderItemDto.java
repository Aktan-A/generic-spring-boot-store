package com.store.api.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class OrderItemDto {
    private Long id;
    private int quantity;
    private Long orderId;
    private Long productId;
    private LocalDateTime createdAt;
}
