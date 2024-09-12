package com.riwi.MealMap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "drinks")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDrink;

    @Column(nullable = false)
    private String nameOfDrink;

    @Column(nullable = false)
    private Double priceOfDrink;

    @ManyToOne
    @JoinColumn(nullable = false)
    private TypeOfDrink typeOfDrink;
}
