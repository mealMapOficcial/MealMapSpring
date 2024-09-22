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
    private Integer id;

    @Column(nullable = false)
    private Integer numberOfChairs;

    @Column(nullable = false)
    private Boolean disponibility;

    public boolean isAvailable() {
        this.disponibility = !this.disponibility;
        return false;
    }
}
