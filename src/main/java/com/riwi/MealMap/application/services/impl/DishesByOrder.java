package com.riwi.MealMap.application.services.impl;

import com.riwi.MealMap.domain.entities.Dish;
import com.riwi.MealMap.domain.entities.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "dishes_by_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DishesByOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDishByOrder;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Dish dish;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Order order;

}
