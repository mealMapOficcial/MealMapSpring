package com.riwi.MealMap.controllers.impl;

import com.riwi.MealMap.dtos.request.Ingredient.DishWithoutId;
import com.riwi.MealMap.entities.Dish;
import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.services.impl.DishService;
import com.riwi.MealMap.services.interfaces.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/dish")
public class DishController implements IDishService {

    @Autowired
    DishService dishService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    @PostMapping("/create")
    public ResponseEntity<Dish> createDTO(DishWithoutId dish) {
        return dishService.createDTO(dish);
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public void delete(@PathVariable Integer id) {
        dishService.delete(id);
    }

    @Override
    @GetMapping("/readAll")
    public List<Dish> readAll() {
        return dishService.readAll();
    }

    @Override
    @GetMapping("/readById/{id}")
    public Optional<Dish> readById(@PathVariable Integer id) {
        String url = "http://localhost:3000/orders";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);

        Optional<Dish> dish = dishService.readById(id);

        return ResponseEntity.ok(dish).getBody();
    }

    @Override
    @GetMapping("/readByName/{name}")
    public ResponseEntity<Dish> readByName(@PathVariable String name) {
        return dishService.readByName(name);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<Dish> update(@PathVariable Integer id,@RequestBody Dish dish) {
        return dishService.update(id, dish);
    }
}
