package com.riwi.MealMap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String id;

    @Column(nullable = false, length = 50)
    private String name;

    @Column(nullable = false, length = 225)
    private String email;

    @OneToMany(mappedBy = "user")
    private Set<Role> roles;

    @OneToMany(mappedBy = "user")
    private Set<Restaurants> restaurants;

    @OneToMany(mappedBy = "user")
    private Set<Orders> orders;
}
