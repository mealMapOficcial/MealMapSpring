package com.riwi.MealMap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany
    @JoinColumn(nullable = false)
    private List<IngredientsByDish> ingredientsByDish;

    private Double valueOfDish() {
        return ingredientsByDish.stream()
                .mapToDouble(ingredientByDish -> ingredientByDish.getIngredient().getPrice())
                .sum();
    }

    //@ManyToOne
    //@JoinColumn(nullable = false)
    //private TypeOfDish typeOfDish;
}
