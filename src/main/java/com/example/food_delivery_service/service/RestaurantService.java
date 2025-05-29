package com.example.food_delivery_service.service;

import com.example.food_delivery_service.dto.RestaurantDto;
import com.example.food_delivery_service.entity.Restaurant;
import com.example.food_delivery_service.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RestaurantService {
    private final RestaurantRepository restaurantRepository;

    public List<RestaurantDto> getAllRestaurants() {
        return restaurantRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public RestaurantDto getRestaurantById(Long id) {
        Restaurant restaurant = restaurantRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));
        return convertToDto(restaurant);
    }

    public RestaurantDto createRestaurant(RestaurantDto restaurantDto) {
        Restaurant restaurant = new Restaurant();
        restaurant.setName(restaurantDto.getName());
        restaurant.setCity(restaurantDto.getCity());
        restaurant.setRating(restaurantDto.getRating());

        Restaurant savedRestaurant = restaurantRepository.save(restaurant);
        return convertToDto(savedRestaurant);
    }

    private RestaurantDto convertToDto(Restaurant restaurant) {
        RestaurantDto dto = new RestaurantDto();
        dto.setId(restaurant.getId());
        dto.setName(restaurant.getName());
        dto.setCity(restaurant.getCity());
        dto.setRating(restaurant.getRating());
        return dto;
    }
}
