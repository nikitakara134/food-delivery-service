package com.example.food_delivery_service.dto;


import lombok.Data;

@Data
public class RestaurantDto {
    private Long id;
    private String name;
    private String city;
    private Double rating;
}