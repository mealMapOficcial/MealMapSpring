package com.riwi.MealMap.infrastructure.persistence;

import com.riwi.MealMap.domain.entities.Floor;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FloorRepository extends JpaRepository<Floor, Integer> {
}
