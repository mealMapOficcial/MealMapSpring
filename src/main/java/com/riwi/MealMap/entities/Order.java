package com.riwi.MealMap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idOrder;

    @Column(nullable = false)
    private Integer quantityOfPlates;

    @Column(nullable = false)
    private Integer quantityOfDrinks;

    //@ManyToOne
    //@JoinColumn(nullable = false)
    //private PayMethod payMethod;

    //@ManyToOne
    //@JoinColumn(nullable = false)
    //private User user;

    //@ManyToOne
    //@JoinColumn(nullable = false)
    //private Table table;

}
