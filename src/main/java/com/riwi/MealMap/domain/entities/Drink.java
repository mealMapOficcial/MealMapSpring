package com.riwi.MealMap.domain.entities;

import java.util.List;

import com.riwi.MealMap.domain.enums.TypeOfDrinks;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


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
