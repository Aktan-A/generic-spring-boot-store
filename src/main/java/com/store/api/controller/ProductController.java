package com.store.api.controller;

import com.store.api.dto.ProductDto;
import com.store.api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "/{productId}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable("productId") Long id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productService.createProduct(productDto));
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<String> deleteProductById(@PathVariable("productId") Long id) {
        productService.deleteProductById(id);
        return ResponseEntity.ok("Product successfully deleted.");
    }

    @PutMapping(path = "/{productId}")
    public ResponseEntity<ProductDto> updateProduct(
            @PathVariable("productId") Long productId,
            @RequestBody ProductDto productDto) {
        return ResponseEntity.ok(productService.updateProductById(productId, productDto));
    }

}
