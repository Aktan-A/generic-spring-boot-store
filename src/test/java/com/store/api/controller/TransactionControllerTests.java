package com.store.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.api.dto.TransactionDto;
import com.store.api.enums.TransactionStatus;
import com.store.api.security.JwtService;
import com.store.api.service.TransactionService;
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

@WebMvcTest(controllers = TransactionController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class TransactionControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private JwtService jwtService;

    @MockBean
    private TransactionService transactionService;

    @Autowired
    private ObjectMapper objectMapper;
    private TransactionDto transactionDtoIn;
    private TransactionDto transactionDtoOut;

    @BeforeEach
    public void init() {
        transactionDtoIn = TransactionDto.builder()
                .orderId(1L)
                .totalPrice(100).build();
        transactionDtoOut = TransactionDto.builder()
                .id(1L)
                .status(TransactionStatus.CREATED)
                .orderId(1L)
                .totalPrice(100)
                .createdAt(LocalDateTime.now()).build();
    }

    @Test
    public void createTransaction_ReturnCreated() throws Exception {
        when(transactionService.createTransaction(Mockito.any(TransactionDto.class))).thenReturn(transactionDtoOut);

        ResultActions response = mockMvc.perform(post("/api/v1/transactions")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionDtoIn)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice", CoreMatchers.is(transactionDtoOut.getTotalPrice())));
    }

    @Test
    public void getAllTransactions_ReturnTransactionDtoList() throws Exception {
        when(transactionService.getTransactions()).thenReturn(List.of(transactionDtoOut));

        ResultActions response = mockMvc.perform(get("/api/v1/transactions"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalPrice", CoreMatchers.is(transactionDtoOut.getTotalPrice())));
    }

    @Test
    public void getTransactionById_ReturnTransactionDto() throws Exception {
        Long transactionId = 1L;
        when(transactionService.getTransactionById(transactionId)).thenReturn(transactionDtoOut);

        ResultActions response = mockMvc.perform(get("/api/v1/transactions/1"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice", CoreMatchers.is(transactionDtoOut.getTotalPrice())));
    }

    @Test
    public void updateTransaction_ReturnTransactionDto() throws Exception {
        Long transactionId = 1L;
        when(transactionService.updateTransactionById(transactionId, transactionDtoIn)).thenReturn(transactionDtoOut);

        ResultActions response = mockMvc.perform(put("/api/v1/transactions/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(transactionDtoIn)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.totalPrice", CoreMatchers.is(transactionDtoOut.getTotalPrice())));
    }

    @Test
    public void deleteTransactionById_ReturnString() throws Exception {
        Long transactionId = 1L;
        doNothing().when(transactionService).deleteTransactionById(transactionId);

        ResultActions response = mockMvc.perform(delete("/api/v1/transactions/1"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is("Transaction successfully deleted.")));
    }
}
