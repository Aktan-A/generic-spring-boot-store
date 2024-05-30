package com.store.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.store.api.dto.ProductDto;
import com.store.api.enums.ProductStatus;
import com.store.api.service.ProductService;
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


@WebMvcTest(controllers = ProductController.class)
@AutoConfigureMockMvc(addFilters = false)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @Autowired
    private ObjectMapper objectMapper;
    private ProductDto productDtoIn;
    private ProductDto productDtoOut;

    @BeforeEach
    public void init() {
        productDtoIn = ProductDto.builder()
                .name("Test product")
                .status(ProductStatus.ACTIVE)
                .description("Test description")
                .price(9.99).build();
        productDtoOut = ProductDto.builder()
                .id(1L)
                .name("Test product")
                .status(ProductStatus.ACTIVE)
                .description("Test description")
                .price(9.99)
                .createdAt(LocalDateTime.now()).build();
    }

    @Test
    public void createProduct_ReturnCreated() throws Exception {
        when(productService.createProduct(Mockito.any(ProductDto.class))).thenReturn(productDtoOut);

        ResultActions response = mockMvc.perform(post("/api/v1/products")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDtoIn)));

        response.andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(productDtoOut.getName())));
    }

    @Test
    public void getAllProducts_ReturnProductDtoList() throws Exception {
        when(productService.getProducts()).thenReturn(List.of(productDtoOut));

        ResultActions response = mockMvc.perform(get("/api/v1/products"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$").isArray())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name", CoreMatchers.is(productDtoOut.getName())));
    }

    @Test
    public void getProductById_ReturnProductDto() throws Exception {
        Long productId = 1L;
        when(productService.getProductById(productId)).thenReturn(productDtoOut);

        ResultActions response = mockMvc.perform(get("/api/v1/products/1"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(productDtoOut.getName())));
    }

    @Test
    public void updateProduct_ReturnProductDto() throws Exception {
        Long productId = 1L;
        when(productService.updateProductById(productId, productDtoIn)).thenReturn(productDtoOut);

        ResultActions response = mockMvc.perform(put("/api/v1/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(productDtoIn)));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.name", CoreMatchers.is(productDtoOut.getName())));
    }

    @Test
    public void deleteProductById_ReturnString() throws Exception {
        Long productId = 1L;
        doNothing().when(productService).deleteProductById(productId);

        ResultActions response = mockMvc.perform(delete("/api/v1/products/1"));

        response.andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", CoreMatchers.is("Product successfully deleted.")));
    }
}
