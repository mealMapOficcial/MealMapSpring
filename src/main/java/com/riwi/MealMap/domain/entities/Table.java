package com.riwi.MealMap.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer idTable;

    @Column(nullable = false)
    private Integer numberOfChairs;

    @Column(nullable = false)
    private Boolean disponibility;

    @Column(nullable = false)
    private Integer floor;

}
