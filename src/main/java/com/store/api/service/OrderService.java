package com.store.api.service;

import com.store.api.dto.OrderDto;
import com.store.api.enums.OrderStatus;
import com.store.api.exception.ResourceNotFoundException;
import com.store.api.mapper.OrderMapper;
import com.store.api.model.Customer;
import com.store.api.model.Order;
import com.store.api.repository.CustomerRepository;
import com.store.api.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public OrderService(
            OrderRepository orderRepository,
            CustomerRepository customerRepository
    ) {
        this.orderRepository = orderRepository;
        this.customerRepository = customerRepository;
    }

    public OrderDto getOrderById(long id) {
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
        Optional<Customer> customer = customerRepository.findById(orderDto.getCustomerId());
        if (customer.isEmpty()) {
            throw new ResourceNotFoundException("Customer with id " + orderDto.getCustomerId() + " does not exist.");
        }

        Order order = OrderMapper.convertDtoToEntity(orderDto);
        order.setStatus(OrderStatus.CREATED);
        order.setCustomer(customer.get());

        return OrderMapper.convertEntityToDto(orderRepository.save(order));
    }

    public void deleteOrderById(long id) {
        boolean exists = orderRepository.existsById(id);
        if (!exists) {
            throw new ResourceNotFoundException("Order with id " + id + " does not exist.");
        }
        orderRepository.deleteById(id);
    }

    public OrderDto updateOrderById(long id, OrderDto orderDto) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isEmpty()) {
            throw new ResourceNotFoundException("Order with id " + id + " does not exist.");
        }
        Order orderModel = order.get();
        orderModel.setStatus(orderDto.getStatus());

        return OrderMapper.convertEntityToDto(orderRepository.save(orderModel));
    }


}
