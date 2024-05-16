package com.store.api.controllers;

import com.store.api.enums.ProductStatus;
import com.store.api.models.Product;
import com.store.api.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Product getProductDetails(@PathVariable("productId") Long id) {
        return productService.getProduct(id);
    }

    @GetMapping
    public List<Product> getProducts() {
        return productService.getProducts();
    }

    @PostMapping
    public void createProduct(@RequestBody Product product) {
        productService.addNewProduct(product);
    }

    @DeleteMapping(path = "{productId}")
    public void deleteProduct(@PathVariable("productId") Long id) {
        productService.deleteProduct(id);
    }

    @PutMapping(path = "{productId}")
    public void updateProduct(
            @PathVariable("productId") Long productId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) ProductStatus status,
            @RequestParam(required = false) String description,
            @RequestParam(required = false) Double price) {
        productService.updateProduct(productId, name, status, description, price);
    }

}
