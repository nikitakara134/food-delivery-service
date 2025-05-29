package com.example.food_delivery_service.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "delivery_orders")
@Data
@NoArgsConstructor
public class DeliveryOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String customerName;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private Double totalPrice;

    @Column(nullable = false)
    private LocalDateTime orderTime = LocalDateTime.now();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "restaurant_id", nullable = false)
    private Restaurant restaurant;
}
