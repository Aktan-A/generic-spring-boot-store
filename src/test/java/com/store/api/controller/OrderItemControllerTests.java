package com.store.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.api.dto.OrderItemDto;
import com.store.api.service.OrderItemService;
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

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@WebMvcTest(controllers = OrderItemController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class OrderItemControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderItemService orderItemService;

    @Autowired
    private ObjectMapper objectMapper;
    private OrderItemDto orderItemDtoIn;
    private OrderItemDto orderItemDtoOut;

    @BeforeEach
    public void init() {
        orderItemDtoIn = OrderItemDto.builder()
                .orderId(1L)
                .productId(1L)
                .quantity(5).build();
        orderItemDtoOut = OrderItemDto.builder()
                .id(1L)
                .orderId(1L)
                .productId(1L)
                .quantity(5)
                .createdAt(LocalDateTime.now()).build();
    }

    @Test
    public void createOrderItem_ReturnCreated() throws Exception {
        when(orderItemService.createOrderItem(Mockito.any(OrderItemDto.class))).thenReturn(orderItemDtoOut);

        ResultActions response = mockMvc.perform(post("/api/v1/order_items")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderItemDtoIn)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", CoreMatchers.is(orderItemDtoOut.getQuantity())));
    }

    @Test
    public void updateOrderItemById_ReturnOrderItemDto() throws Exception {
        Long orderItemId = 1L;
        when(orderItemService.updateOrderItemById(orderItemId, orderItemDtoIn)).thenReturn(orderItemDtoOut);

        ResultActions response = mockMvc.perform(put("/api/v1/order_items/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(orderItemDtoIn)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.quantity", CoreMatchers.is(orderItemDtoOut.getQuantity())));
    }

    @Test
    public void deleteOrderItemById_ReturnString() throws Exception {
        Long orderItemId = 1L;
        doNothing().when(orderItemService).deleteOrderItemById(orderItemId);

        ResultActions response = mockMvc.perform(delete("/api/v1/order_items/1"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is("Order item successfully deleted.")));
    }
}
