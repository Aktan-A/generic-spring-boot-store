package com.store.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.api.dto.OrderDto;
import com.store.api.enums.OrderStatus;
import com.store.api.service.OrderService;
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

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = OrderController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class OrderControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderService orderService;

    @Autowired
    private ObjectMapper objectMapper;

    private OrderDto orderDtoIn;
    private OrderDto orderDtoOut;

    @BeforeEach
    public void init() {
        orderDtoIn = OrderDto.builder()
                .userId(1L).build();
        orderDtoOut = OrderDto.builder()
                .id(1L)
                .userId(1L)
                .status(OrderStatus.CREATED)
                .createdAt(LocalDateTime.now()).build();
    }

    @Test
    public void createOrder_ReturnCreated() throws Exception {
        when(orderService.createOrder(Mockito.any(OrderDto.class))).thenReturn(orderDtoOut);

        ResultActions response = mockMvc.perform(post("/api/v1/orders")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDtoIn)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", CoreMatchers.is(orderDtoOut.getUserId().intValue())));
    }

    @Test
    public void getAllOrders_ReturnOrderDtoList() throws Exception {
        when(orderService.getAllOrders()).thenReturn(List.of(orderDtoOut));

        ResultActions response = mockMvc.perform(get("/api/v1/orders"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].userId", CoreMatchers.is(orderDtoOut.getUserId().intValue())));
    }

    @Test
    public void getOrderById_ReturnOrderDto() throws Exception {
        Long orderId = 1L;
        when(orderService.getOrderById(orderId)).thenReturn(orderDtoOut);

        ResultActions response = mockMvc.perform(get("/api/v1/orders/1"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", CoreMatchers.is(orderDtoOut.getUserId().intValue())));
    }

    @Test
    public void updateOrderById_ReturnOrderDto() throws Exception {
        Long orderId = 1L;
        OrderDto orderDto = OrderDto.builder().status(OrderStatus.CREATED).build();
        when(orderService.updateOrderById(orderId, orderDto)).thenReturn(orderDtoOut);

        ResultActions response = mockMvc.perform(put("/api/v1/orders/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderDto)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.userId", CoreMatchers.is(orderDtoOut.getUserId().intValue())));
    }

    @Test
    public void deleteOrderById_ReturnString() throws Exception {
        Long orderId = 1L;
        doNothing().when(orderService).deleteOrderById(orderId);

        ResultActions response = mockMvc.perform(delete("/api/v1/orders/1"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is("Order successfully deleted.")));
    }


}
