package com.store.api.dto;

import com.store.api.enums.UserRole;
import lombok.Data;

@Data
public class RegisterDto {
    String username;
    String password;
    UserRole role;
    String firstName;
    String lastName;
    String address;
}
