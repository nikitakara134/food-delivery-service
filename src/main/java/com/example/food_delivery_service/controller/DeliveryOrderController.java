package com.example.food_delivery_service.controller;

import com.example.food_delivery_service.dto.DeliveryOrderDto;
import com.example.food_delivery_service.service.DeliveryOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class DeliveryOrderController {
    private final DeliveryOrderService orderService;

    @PostMapping
    public ResponseEntity<DeliveryOrderDto> createOrder(@RequestBody DeliveryOrderDto orderDto) {
        return ResponseEntity.ok(orderService.createOrder(orderDto));
    }

    @GetMapping("/by-customer")
    public ResponseEntity<List<DeliveryOrderDto>> getOrdersByCustomer(@RequestParam String customerName) {
        return ResponseEntity.ok(orderService.getOrdersByCustomer(customerName));
    }
}