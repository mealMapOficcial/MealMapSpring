package com.riwi.MealMapSpring.Repository.Interfaces;

import com.riwi.MealMapSpring.Entities.Dishes;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DishRepository  extends JpaRepository<Dishes,Long> {
}
