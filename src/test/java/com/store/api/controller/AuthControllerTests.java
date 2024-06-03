package com.store.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.api.dto.RegisterRequestDto;
import com.store.api.dto.RegisterResponseDto;
import com.store.api.security.JwtService;
import com.store.api.service.AuthService;
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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = AuthController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class AuthControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AuthService authService;

    @MockBean
    private JwtService jwtService;

    @Autowired
    private ObjectMapper objectMapper;

    private RegisterResponseDto registerResponseDto;

    @BeforeEach
    public void init() {
        registerResponseDto = RegisterResponseDto.builder()
                .accessToken("test-token").build();
    }

    @Test
    public void register_ReturnRegisterResponseDto() throws Exception {
        RegisterRequestDto registerRequestDto = RegisterRequestDto.builder()
                .username("johndoe")
                .password("123")
                .firstName("John")
                .lastName("Doe")
                .address("That Avenue 28").build();
        when(authService.register(Mockito.any(RegisterRequestDto.class))).thenReturn(registerResponseDto);

        ResultActions response = mockMvc.perform(post("/api/v1/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(registerRequestDto)));
        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.accessToken", CoreMatchers.is(registerResponseDto.getAccessToken())));
    }
}
