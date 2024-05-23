package com.store.api.dto;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class CustomerDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String address;
    private LocalDateTime createdAt;

    public CustomerDto(
            Long id,
            String firstName,
            String lastName,
            String address,
            LocalDateTime createdAt
    ) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.createdAt = createdAt;
    }
}
