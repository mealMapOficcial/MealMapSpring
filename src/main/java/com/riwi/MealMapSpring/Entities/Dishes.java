package com.riwi.MealMapSpring.Entities;

import com.riwi.MealMapSpring.Enum.TypeOfDishes;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Entity
@Table(name = "dishes")
public class Dishes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)

    @PrePersist
    public void prePersist() {
        promotion = false;
    }
    @Column(nullable = false)
    private boolean promotion;

    @Enumerated(EnumType.STRING)
    private TypeOfDishes typeOfDishes;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dishes_ingredients",
            joinColumns = @JoinColumn(name = "dishes_id"),
            inverseJoinColumns = @JoinColumn(name = "ingredients_id"))
    private List<Ingredients> ingredients;

}
