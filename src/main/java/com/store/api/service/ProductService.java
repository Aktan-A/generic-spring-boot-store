package com.store.api.service;

import com.store.api.enums.ProductStatus;
import com.store.api.exception.ResourceNotFoundException;
import com.store.api.model.Product;
import com.store.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product getProduct(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " does not exist."));
    }

    public List<Product> getProducts() {
        return productRepository.findAll();
    }

    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Product with id " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }

    public Product updateProduct(Long productId,
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

        return productRepository.save(product);
    }
}
