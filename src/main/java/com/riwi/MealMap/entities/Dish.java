package com.riwi.MealMap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "dishes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idDish;

    @Column(nullable = false)
    private String nameOfDish;

    @Column(nullable = false)
    private Double priceOfDish;

    //@ManyToOne
    //@JoinColumn(nullable = false)
    //private TypeOfDish typeOfDish;
}
