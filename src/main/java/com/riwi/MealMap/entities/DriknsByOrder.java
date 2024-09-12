package com.riwi.MealMap.entities;

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
