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
    @Enumerated(EnumType.STRING)
    private TypeOfDishes typeOfDishes;
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "dishes_ingredients",
    joinColumns =  @JoinColumn(name = "dishes_id" ),
    inverseJoinColumns = @JoinColumn(name = "ingredients_id"))
    private List<Ingredients> ingredients;

}
