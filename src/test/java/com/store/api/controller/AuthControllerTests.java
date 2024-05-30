package com.store.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.api.dto.RegisterDto;
import com.store.api.dto.UserDto;
import com.store.api.enums.UserRole;
import com.store.api.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthenticationManager authenticationManager;

    @MockBean
    private UserService userService;

    @MockBean
    private PasswordEncoder passwordEncoder;
    @Autowired
    private ObjectMapper objectMapper;
    private UserDto userDtoOut;

    @BeforeEach
    public void init() {
        userDtoOut = UserDto.builder()
                .id(1L)
                .username("johndoe")
                .password("123")
                .firstName("John")
                .lastName("Doe")
                .address("That Avenue 28")
                .role(UserRole.CUSTOMER)
                .createdAt(LocalDateTime.now()).build();
    }

    @Test
    public void register_ReturnUserDto() throws Exception {
        RegisterDto registerDto = RegisterDto.builder()
                .username("johndoe")
                .password("123")
                .firstName("John")
                .lastName("Doe")
                .address("That Avenue 28")
                .role(UserRole.CUSTOMER).build();
        when(passwordEncoder.encode(Mockito.anyString())).thenReturn("MTIz");
        when(userService.registerUser(Mockito.any(RegisterDto.class))).thenReturn(userDtoOut);

        ResultActions response = mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerDto)));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.username", CoreMatchers.is(userDtoOut.getUsername())))
                .andDo(MockMvcResultHandlers.print());
    }
}
