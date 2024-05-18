package com.store.api.controller;

import com.store.api.enums.ProductStatus;
import com.store.api.model.Product;
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
    public Product getProductById(@PathVariable("productId") Long id) {
        return productService.getProductById(id);
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    @ResponseStatus(value = HttpStatus.CREATED)
    public Product createProduct(@RequestBody Product product) {
        return productService.addNewProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteProduct(@PathVariable("productId") Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping(path = "{productId}")
    public Product updateProduct(
            @PathVariable("productId") Long productId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) ProductStatus status,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price) {
        return productService.updateProduct(productId, name, status, description, price);
    }

}
