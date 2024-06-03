package com.store.api.controller;

import com.store.api.dto.OrderDto;
import com.store.api.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping(path = "/{orderId}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable("orderId") Long id) {
        return ResponseEntity.ok(orderService.getOrderById(id));
    }

    @GetMapping
    public ResponseEntity<List<OrderDto>> getAllOrders() {
        return ResponseEntity.ok(orderService.getAllOrders());
    }

    @PostMapping
    public ResponseEntity<OrderDto> createOrder(@RequestBody OrderDto orderDto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(orderDto));
    }

    @DeleteMapping(path = "/{orderId}")
    public ResponseEntity<String> deleteOrderById(@PathVariable("orderId") Long id) {
        orderService.deleteOrderById(id);
        return ResponseEntity.ok("Order successfully deleted.");
    }

    @PutMapping(path = "/{orderId}")
    public ResponseEntity<OrderDto> updateOrderById(@PathVariable("orderId") Long id, @RequestBody OrderDto orderDto) {
        return ResponseEntity.ok(orderService.updateOrderById(id, orderDto));
    }

}
