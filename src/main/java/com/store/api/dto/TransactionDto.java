package com.store.api.dto;

import com.store.api.enums.TransactionStatus;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
@Builder
public class TransactionDto {
    private Long id;
    private double totalPrice;
    private Long orderId;
    private TransactionStatus status;
    private LocalDateTime createdAt;
}
