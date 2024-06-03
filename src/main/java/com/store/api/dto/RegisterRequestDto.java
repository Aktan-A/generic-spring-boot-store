package com.store.api.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequestDto {
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String address;
}
