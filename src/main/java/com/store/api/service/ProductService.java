package com.store.api.service;

import com.store.api.dto.ProductDto;
import com.store.api.exception.ResourceNotFoundException;
import com.store.api.mapper.ProductMapper;
import com.store.api.model.Product;
import com.store.api.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public ProductDto getProductById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Product with id " + id + " does not exist.");
        }
        return ProductMapper.convertEntityToDto(product.get());
    }

    public List<ProductDto> getProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(ProductMapper::convertEntityToDto).collect(Collectors.toList());
    }

    public ProductDto addNewProduct(ProductDto productDto) {
        Product product = ProductMapper.convertDtoToEntity(productDto);
        return ProductMapper.convertEntityToDto(productRepository.save(product));
    }

    public void deleteProductById(Long id) {
        boolean exists = productRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Product with id " + id + " does not exist.");
        }
        productRepository.deleteById(id);
    }

    public ProductDto updateProductById(Long productId,
                                        ProductDto productDto) {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Product with id " + productId + " does not exist.");
        }
        Product productModel = product.get();
        productModel.setName(productDto.getName());
        productModel.setStatus(productDto.getStatus());
        productModel.setDescription(productDto.getDescription());
        productModel.setPrice(productDto.getPrice());

        return ProductMapper.convertEntityToDto(productRepository.save(productModel));
    }
}
