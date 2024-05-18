package com.store.api.controller;

import com.store.api.dto.ProductDto;
import com.store.api.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/products")
public class ProductController {

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping(path = "{productId}")
    public ProductDto getProductById(@PathVariable("productId") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<ProductDto> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public ProductDto createProduct(@RequestBody ProductDto productDto) {
        return productService.addNewProduct(productDto);
    }

    @DeleteMapping(path = "{productId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") Long id) {
        productService.deleteProductById(id);
    }

    @PutMapping(path = "{productId}")
    public ProductDto updateProduct(
            @PathVariable("productId") Long productId,
            @RequestBody ProductDto productDto) {
        return productService.updateProductById(productId, productDto);
    }

}
