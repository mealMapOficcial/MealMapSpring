package com.riwi.MealMap.domain.entities;

import com.riwi.MealMap.domain.enums.TypeOfDishes;
import com.riwi.MealMap.domain.enums.TypeOfDrinks;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "drinks")
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

    @Column(nullable = false)
    private boolean promotion;

    @Enumerated(EnumType.STRING)
    private TypeOfDrinks typeOfDrinks;

    @ManyToMany
    @JoinTable(
            name = "drinks_ingredients",
            joinColumns = @JoinColumn(name = "drinks_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id")
    )
    private List<Ingredient> ingredients;
}
