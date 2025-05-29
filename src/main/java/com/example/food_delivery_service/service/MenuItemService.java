package com.example.food_delivery_service.service;

import com.example.food_delivery_service.dto.MenuItemDto;
import com.example.food_delivery_service.entity.MenuItem;
import com.example.food_delivery_service.entity.Restaurant;
import com.example.food_delivery_service.repository.MenuItemRepository;
import com.example.food_delivery_service.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MenuItemService {
    private final MenuItemRepository menuItemRepository;
    private final RestaurantRepository restaurantRepository;

    public List<MenuItemDto> getMenuByRestaurantId(Long restaurantId) {
        return menuItemRepository.findByRestaurantId(restaurantId).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    public MenuItemDto createMenuItem(MenuItemDto menuItemDto) {
        Restaurant restaurant = restaurantRepository.findById(menuItemDto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        MenuItem menuItem = new MenuItem();
        menuItem.setName(menuItemDto.getName());
        menuItem.setPrice(menuItemDto.getPrice());
        menuItem.setRestaurant(restaurant);

        MenuItem savedItem = menuItemRepository.save(menuItem);
        return convertToDto(savedItem);
    }

    private MenuItemDto convertToDto(MenuItem menuItem) {
        MenuItemDto dto = new MenuItemDto();
        dto.setId(menuItem.getId());
        dto.setName(menuItem.getName());
        dto.setPrice(menuItem.getPrice());
        dto.setRestaurantId(menuItem.getRestaurant().getId());
        return dto;
    }
}