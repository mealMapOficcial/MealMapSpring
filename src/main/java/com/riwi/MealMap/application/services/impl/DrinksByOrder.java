package com.riwi.MealMap.application.services.impl;

import com.riwi.MealMap.domain.entities.Drink;
import com.riwi.MealMap.domain.entities.Order;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "drinks_by_orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DrinksByOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDrinkByOrder;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Drink drink;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Order order;

}
