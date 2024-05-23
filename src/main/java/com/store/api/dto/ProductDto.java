package com.store.api.dto;

import java.time.LocalDateTime;
import java.util.Objects;

import com.store.api.enums.ProductStatus;
import lombok.*;


@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class ProductDto {
    private Long id;
    private String name;
    private ProductStatus status;
    private String description;
    private Double price;
    private LocalDateTime createdAt;

    public ProductDto(Long id, String name, ProductStatus status, String description, Double price,
                      LocalDateTime createdAt) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.description = description;
        this.price = price;
        this.createdAt = createdAt;
    }

}
