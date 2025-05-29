package com.example.food_delivery_service.controller;

import com.example.food_delivery_service.dto.MenuItemDto;
import com.example.food_delivery_service.service.MenuItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/menu")
@RequiredArgsConstructor
public class MenuItemController {
    private final MenuItemService menuItemService;

    @GetMapping("/{restaurantId}")
    public ResponseEntity<List<MenuItemDto>> getMenuByRestaurantId(@PathVariable Long restaurantId) {
        return ResponseEntity.ok(menuItemService.getMenuByRestaurantId(restaurantId));
    }

    @PostMapping
    public ResponseEntity<MenuItemDto> createMenuItem(@RequestBody MenuItemDto menuItemDto) {
        return ResponseEntity.ok(menuItemService.createMenuItem(menuItemDto));
    }
}