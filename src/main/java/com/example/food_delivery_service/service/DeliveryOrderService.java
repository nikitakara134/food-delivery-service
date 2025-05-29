package com.example.food_delivery_service.service;

import com.example.food_delivery_service.dto.DeliveryOrderDto;
import com.example.food_delivery_service.entity.DeliveryOrder;
import com.example.food_delivery_service.entity.Restaurant;
import com.example.food_delivery_service.repository.DeliveryOrderRepository;
import com.example.food_delivery_service.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DeliveryOrderService {
    private final DeliveryOrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;

    public DeliveryOrderDto createOrder(DeliveryOrderDto orderDto) {
        Restaurant restaurant = restaurantRepository.findById(orderDto.getRestaurantId())
                .orElseThrow(() -> new RuntimeException("Restaurant not found"));

        DeliveryOrder order = new DeliveryOrder();
        order.setCustomerName(orderDto.getCustomerName());
        order.setAddress(orderDto.getAddress());
        order.setTotalPrice(orderDto.getTotalPrice());
        order.setRestaurant(restaurant);

        DeliveryOrder savedOrder = orderRepository.save(order);
        return convertToDto(savedOrder);
    }

    public List<DeliveryOrderDto> getOrdersByCustomer(String customerName) {
        return orderRepository.findByCustomerName(customerName).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private DeliveryOrderDto convertToDto(DeliveryOrder order) {
        DeliveryOrderDto dto = new DeliveryOrderDto();
        dto.setId(order.getId());
        dto.setCustomerName(order.getCustomerName());
        dto.setAddress(order.getAddress());
        dto.setTotalPrice(order.getTotalPrice());
        dto.setOrderTime(order.getOrderTime());
        dto.setRestaurantId(order.getRestaurant().getId());
        return dto;
    }
}