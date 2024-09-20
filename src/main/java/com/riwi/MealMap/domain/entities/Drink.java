package com.riwi.MealMap.domain.entities;

import com.riwi.MealMap.domain.enums.TypeOfDrinks;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity(name = "drinks")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Drink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Float price;

    @PrePersist
    public void prePersist() {
        promotion = false;
    @OneToMany
    @JoinColumn(nullable = false)
    private List<IngredientsByDrink> ingredientsByDrink;

    public Double valueOfDrink() {
        return ingredientsByDrink.stream()
                .mapToDouble(ingredientByDrink -> ingredientByDrink.getIngredient().getPrice())
                .sum();
    }
    @Column(nullable = false)
    private boolean promotion;

    @Enumerated(EnumType.STRING)
    private TypeOfDrinks typeOfDrinks;

    @ManyToMany
    @JoinTable(
            name = "ingredients_by_drinks",
            joinColumns = @JoinColumn(name = "drink_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredient_id")
    )
    private List<Ingredient> ingredients;
}
