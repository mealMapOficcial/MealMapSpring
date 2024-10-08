package com.riwi.MealMap.domain.entities;

import com.riwi.MealMap.domain.enums.TypeOfDishes;
import jakarta.persistence.*;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Entity
@Table(name = "dishes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Dish {
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
    }
    @Column(nullable = false)
    private boolean promotion;

    @Enumerated(EnumType.STRING)
    private TypeOfDishes typeOfDishes;

    @Column(name = "image_url", length = 1500)
    private String imageUrl;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "dishes_ingredients",
            joinColumns = @JoinColumn(name = "dishes_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id")
    )
    private List<Ingredient> ingredients;
}
