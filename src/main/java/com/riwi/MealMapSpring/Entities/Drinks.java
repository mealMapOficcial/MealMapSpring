package com.riwi.MealMapSpring.Entities;

import com.riwi.MealMapSpring.Enum.TypeOfDrinks;
import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Entity
@Table(name = "drink")
public class Drinks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TypeOfDrinks typeOfDrinks;
}
