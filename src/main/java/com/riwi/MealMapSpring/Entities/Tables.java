package com.riwi.MealMapSpring.Entities;

import jakarta.persistence.*;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Entity
@Table(name = "tables")
public class Tables {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private int numberTable;

    @Column(nullable = false)
    private boolean available = true;

    @Column(nullable = false)
    private int capacity;

    public void toggleAvaiable() {
        this.available = !this.available;
    }

}
