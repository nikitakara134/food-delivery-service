package com.example.food_delivery_service.dto;

import lombok.Data;

@Data
public class MenuItemDto {
    private Long id;
    private String name;
    private Double price;
    private Long restaurantId;
}