package com.riwi.MealMap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Orders {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(nullable = false)
    private Integer quantityOfPersons;

    @ManyToOne
    @JoinColumn(nullable = false)
    private UserEntity user;

    @JoinColumn(nullable = false)
    @ManyToOne
    private Tables table;
}
