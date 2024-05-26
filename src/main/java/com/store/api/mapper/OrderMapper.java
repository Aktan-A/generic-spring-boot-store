package com.store.api.mapper;

import com.store.api.dto.OrderDto;
import com.store.api.model.Order;

public class OrderMapper {
    public static OrderDto convertEntityToDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setId(order.getId());
        orderDto.setUserId(order.getUser().getId());
        orderDto.setStatus(order.getStatus());
        orderDto.setCreatedAt(order.getCreatedAt());
        return orderDto;
    }

    public static Order convertDtoToEntity(OrderDto orderDto) {
        Order order = new Order();
        order.setId(orderDto.getId());
        order.setStatus(orderDto.getStatus());
        order.setCreatedAt(orderDto.getCreatedAt());
        return order;
    }
}
