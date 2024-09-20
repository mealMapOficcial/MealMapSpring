package com.riwi.MealMap.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

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

    @OneToMany
    @JoinColumn(nullable = false)
    private List<IngredientsByDrink> ingredientsByDrink;

    public Double valueOfDrink() {
        return ingredientsByDrink.stream()
                .mapToDouble(ingredientByDrink -> ingredientByDrink.getIngredient().getPrice())
                .sum();
    }

//    @ManyToOne
//    @JoinColumn(nullable = false)
//    private TypeOfDrink typeOfDrink;
}
