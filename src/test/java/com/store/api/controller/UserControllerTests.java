package com.store.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.api.dto.UserDto;
import com.store.api.enums.UserRole;
import com.store.api.security.JwtService;
import com.store.api.service.UserService;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = UserController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class UserControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;
    private UserDto userDtoIn;
    private UserDto userDtoOut;

    @BeforeEach
    public void init() {
        userDtoIn = UserDto.builder()
                .firstName("John")
                .lastName("Doe")
                .address("That Avenue 28").build();
        userDtoOut = UserDto.builder()
                .id(1L)
                .username("johndoe")
                .password("123")
                .firstName("John")
                .lastName("Doe")
                .address("That Avenue 28")
                .role(UserRole.USER)
                .createdAt(LocalDateTime.now()).build();

    }

    @Test
    public void getAllUsers_ReturnUserDtoList() throws Exception {
        when(userService.getUsers()).thenReturn(List.of(userDtoOut));

        ResultActions response = mockMvc.perform(get("/api/v1/users"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName", CoreMatchers.is(userDtoOut.getFirstName())));
    }

    @Test
    public void getUserById_ReturnUserDto() throws Exception {
        Long userId = 1L;
        when(userService.getUserById(userId)).thenReturn(userDtoOut);

        ResultActions response = mockMvc.perform(get("/api/v1/users/1"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(userDtoOut.getFirstName())));
    }

    @Test
    public void updateUser_ReturnUserDto() throws Exception {
        Long userId = 1L;
        when(userService.updateUserById(userId, userDtoIn)).thenReturn(userDtoOut);

        ResultActions response = mockMvc.perform(put("/api/v1/users/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(userDtoIn)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName", CoreMatchers.is(userDtoOut.getFirstName())));
    }

    @Test
    public void deleteUser_ReturnString() throws Exception {
        Long userId = 1L;
        doNothing().when(userService).deleteUserById(userId);

        ResultActions response = mockMvc.perform(delete("/api/v1/users/1"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is("User successfully deleted.")));
    }
}
