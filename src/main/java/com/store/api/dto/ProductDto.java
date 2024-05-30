package com.store.api.dto;

import java.time.LocalDateTime;

import com.store.api.enums.ProductStatus;
import lombok.*;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private ProductStatus status;
    private String description;
    private Double price;
    private LocalDateTime createdAt;
}
