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
    private LocalDateTime createdAt;
}
