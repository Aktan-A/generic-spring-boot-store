package com.store.api.controller;

import com.store.api.dto.OrderItemDto;
import com.store.api.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/order_items")
@RequiredArgsConstructor
public class OrderItemController {

    private final OrderItemService orderItemService;

    @PostMapping
    public ResponseEntity<OrderItemDto> createOrderItem(
            @RequestBody OrderItemDto orderItemDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderItemService.createOrderItem(orderItemDto));
    }

    @DeleteMapping(path = "/{orderItemId}")
    public ResponseEntity<String> deleteOrderItemById(@PathVariable("orderItemId") Long id) {
        orderItemService.deleteOrderItemById(id);
        return ResponseEntity.ok("Order item successfully deleted.");
    }

    @PutMapping(path = "/{orderItemId}")
    public ResponseEntity<OrderItemDto> updateOrderItemById(
            @PathVariable("orderItemId") Long id,
            @RequestBody OrderItemDto orderItemDto) {
        return ResponseEntity.ok(orderItemService.updateOrderItemById(id, orderItemDto));
    }
}
