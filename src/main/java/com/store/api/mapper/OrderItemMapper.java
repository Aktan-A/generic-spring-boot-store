package com.store.api.mapper;

import com.store.api.dto.OrderItemDto;
import com.store.api.model.OrderItem;

public class OrderItemMapper {

    public static OrderItemDto convertEntityToDto(OrderItem orderItem) {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(orderItem.getId());
        orderItemDto.setQuantity(orderItem.getQuantity());
        orderItemDto.setOrderId(orderItem.getOrder().getId());
        orderItemDto.setProductId(orderItem.getProduct().getId());
        orderItemDto.setCreatedAt(orderItem.getCreatedAt());
        return orderItemDto;
    }

    public static OrderItem convertDtoToEntity(OrderItemDto orderItemDto) {
        OrderItem orderItem = new OrderItem();
        orderItem.setId(orderItemDto.getId());
        orderItem.setQuantity(orderItemDto.getQuantity());
        orderItem.setCreatedAt(orderItemDto.getCreatedAt());
        return orderItem;
    }
}
