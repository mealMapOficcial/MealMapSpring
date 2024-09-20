package com.riwi.MealMap.domain.entities;

import jakarta.persistence.Table;
import jakarta.persistence.*;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "stock")
public class Stock {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ingredient_id", unique = true)
    private Ingredient ingredients;

    @Column(nullable = false)
    private double amount;
}
