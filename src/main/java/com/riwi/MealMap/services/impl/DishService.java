package com.riwi.MealMap.services.impl;

import com.riwi.MealMap.dtos.request.Ingredient.DishWithoutId;
import com.riwi.MealMap.entities.Dish;
import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.interfaces.DishRepository;
import com.riwi.MealMap.services.interfaces.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService implements IDishService {

    @Autowired
    DishRepository dishRepository;

    @Override
    public ResponseEntity<Dish> createDTO(DishWithoutId dishDTO) {

        Dish dish = Dish.builder()
                .name(dishDTO.getName())
                .price(dishDTO.getPrice())
                .typeDishes(dishDTO.getTypeDishes())
                .ingredients(dishDTO.getIngredient())
                .build();

        Dish savedDish = dishRepository.save(dish);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDish);
    }

    @Override
    public void delete(Integer id) {
        dishRepository.deleteById(id);
    }

    @Override
    public List<Dish> readAll() {
        return dishRepository.findAll();
    }

    @Override
    public Optional<Dish> readById(Integer id) {
        return dishRepository.findById(id);
    }

    @Override
    public ResponseEntity<Dish> readByName(String name) {

        Optional<Dish> dish = dishRepository.findByName(name);

        if (dish.isPresent()) {
            return ResponseEntity.ok(dish.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Dish> update(Integer id, Dish dish) {
        Dish existingDish = dishRepository.findById(id).orElse(null);

        if (existingDish != null) {

            existingDish.setName(dish.getName());
            existingDish.setPrice(dish.getPrice());
            existingDish.setTypeDishes(dish.getTypeDishes());
            existingDish.setIngredients(dish.getIngredients());

            Dish savedDish = dishRepository.save(existingDish);
            return ResponseEntity.ok(savedDish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
