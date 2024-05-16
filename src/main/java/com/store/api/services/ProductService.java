package com.store.api.services;

import com.store.api.enums.ProductStatus;
import com.store.api.exceptions.ResourceNotFoundException;
import com.store.api.models.Product;
import com.store.api.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public void addNewProduct(Product product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Product with id " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }

    public void updateProduct(Long productId,
                              String name,
                              ProductStatus status,
                              String description,
                              Double price) {
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + productId + " does not exist."));
        if (name != null && !name.isEmpty() && !name.equals(product.getName())) {
            product.setName(name);
        }

        if (status != null) {
            product.setStatus(status);
        }

        if (description != null && !description.equals(product.getDescription())) {
            product.setDescription(description);
        }

        if (price != null) {
            product.setPrice(price);
        }

        productRepository.save(product);
    }
}
