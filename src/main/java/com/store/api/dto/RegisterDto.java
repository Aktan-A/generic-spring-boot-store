package com.store.api.dto;

import com.store.api.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterDto {
    private String username;
    private String password;
    private UserRole role;
    private String firstName;
    private String lastName;
    private String address;
}
