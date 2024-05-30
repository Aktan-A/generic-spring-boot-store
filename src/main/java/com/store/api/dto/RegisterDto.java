package com.store.api.dto;

import com.store.api.enums.UserRole;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterDto {
    String username;
    String password;
    UserRole role;
    String firstName;
    String lastName;
    String address;
}
