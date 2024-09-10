package com.riwi.MealMap.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permitions {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Boolean canCreate;
    private Boolean canUpdate;
    private Boolean canDelete;
    private Boolean canGet;

    @OneToMany(mappedBy = "permitions")
    private Set<Role> roles;

    @OneToMany(mappedBy = "permitions")
    private Set<Entities> entities;
}
