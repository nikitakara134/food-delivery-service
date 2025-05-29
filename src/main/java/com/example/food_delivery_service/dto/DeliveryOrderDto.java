package com.example.food_delivery_service.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DeliveryOrderDto {
    private Long id;
    private String customerName;
    private String address;
    private Double totalPrice;
    private LocalDateTime orderTime;
    private Long restaurantId;
}