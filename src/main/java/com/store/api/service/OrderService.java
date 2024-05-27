package com.store.api.service;

import com.store.api.dto.OrderDto;
import com.store.api.enums.OrderStatus;
import com.store.api.exception.ResourceNotFoundException;
import com.store.api.mapper.OrderMapper;
import com.store.api.model.User;
import com.store.api.model.Order;
import com.store.api.repository.UserRepository;
import com.store.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;

    @Autowired
    public OrderService(
            OrderRepository orderRepository,
            UserRepository userRepository
    ) {
        this.orderRepository = orderRepository;
        this.userRepository = userRepository;
    }

    public OrderDto getOrderById(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new ResourceNotFoundException("Order with id " + id + " does not exist.");
        }
        return OrderMapper.convertEntityToDto(order.get());
    }

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream().map(OrderMapper::convertEntityToDto).collect(Collectors.toList());
    }

    public OrderDto createOrder(OrderDto orderDto) {
        Optional<User> user = userRepository.findById(orderDto.getUserId());
        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User with id " + orderDto.getUserId() + " does not exist.");
        }

        Order order = OrderMapper.convertDtoToEntity(orderDto);
        order.setStatus(OrderStatus.CREATED);
        order.setUser(user.get());

        return OrderMapper.convertEntityToDto(orderRepository.save(order));
    }

    public void deleteOrderById(Long id) {
        boolean exists = orderRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Order with id " + id + " does not exist.");
        }
        orderRepository.deleteById(id);
    }

    public OrderDto updateOrderById(Long id, OrderDto orderDto) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new ResourceNotFoundException("Order with id " + id + " does not exist.");
        }
        Order orderModel = order.get();
        orderModel.setStatus(orderDto.getStatus());

        return OrderMapper.convertEntityToDto(orderRepository.save(orderModel));
    }


}
