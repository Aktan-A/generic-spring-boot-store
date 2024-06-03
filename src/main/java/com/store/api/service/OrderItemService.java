package com.store.api.service;

import com.store.api.dto.OrderItemDto;
import com.store.api.exception.ResourceNotFoundException;
import com.store.api.mapper.OrderItemMapper;
import com.store.api.model.Order;
import com.store.api.model.OrderItem;
import com.store.api.model.Product;
import com.store.api.repository.OrderItemRepository;
import com.store.api.repository.OrderRepository;
import com.store.api.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderItemService {

    private final OrderItemRepository orderItemRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        Optional<Order> order = orderRepository.findById(orderItemDto.getOrderId());
        if (order.isEmpty()) {
            throw new ResourceNotFoundException("Order with id " + orderItemDto.getOrderId() + " does not exist.");
        }

        Optional<Product> product = productRepository.findById(orderItemDto.getProductId());
        if (product.isEmpty()) {
            throw new ResourceNotFoundException("Product with id " + orderItemDto.getProductId() + " does not exist.");
        }

        OrderItem orderItem = OrderItemMapper.convertDtoToEntity(orderItemDto);
        orderItem.setOrder(order.get());
        orderItem.setProduct(product.get());

        return OrderItemMapper.convertEntityToDto(orderItemRepository.save(orderItem));
    }

    public void deleteOrderItemById(Long id) {
        boolean exists = orderItemRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Order item with id " + id + " does not exist.");
        }
        orderItemRepository.deleteById(id);
    }

    public OrderItemDto updateOrderItemById(Long id, OrderItemDto orderItemDto) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(id);
        if (orderItem.isEmpty()) {
            throw new ResourceNotFoundException("Product with id " + orderItemDto.getProductId() + " does not exist.");
        }
        OrderItem orderItemModel = orderItem.get();
        orderItemModel.setQuantity(orderItemDto.getQuantity());

        return OrderItemMapper.convertEntityToDto(orderItemRepository.save(orderItemModel));
    }
}
