package com.store.api.dto;

import com.store.api.enums.OrderStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class OrderDto {
    private Long id;
    private Long userId;
    private OrderStatus status;
    private LocalDateTime createdAt;
}
