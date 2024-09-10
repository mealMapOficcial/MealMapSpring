package com.riwi.MealMap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer numberOfChairs;

    @Column(nullable = false)
    private Boolean disponibility;

    @ManyToOne
    @JoinColumn(nullable = false)
    private Floors floor;

    @OneToMany(mappedBy = "tables")
    private Orders orders;
}
