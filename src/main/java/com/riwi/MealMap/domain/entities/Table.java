package com.riwi.MealMap.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "tables")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Table {
    @Id
    @Column(name = "id", unique = true)
    private Integer idTable;

    @Column(nullable = false)
    private Integer numberOfChairs;

    @Column(nullable = false)
    private Boolean disponibility;

    @Column(nullable = false)
    private Integer floor;

}
