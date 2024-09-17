package com.riwi.MealMap.controllers.impl;

import com.riwi.MealMap.dtos.request.Ingredient.TypeDishWithName;
import com.riwi.MealMap.entities.TypeDish;
import com.riwi.MealMap.services.impl.TypeDishService;
import com.riwi.MealMap.services.interfaces.ITypeDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/typeDish")
public class TypeDishController implements ITypeDishService {
    @Autowired
    TypeDishService typeDishService;

    @Autowired
    RestTemplate restTemplate;

    @Override
    @PostMapping("/create")
    public ResponseEntity<TypeDish> createDTO(TypeDishWithName typeDish) {
        return typeDishService.createDTO(typeDish);
    }

    @Override
    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Integer id) {
        typeDishService.delete(id);
    }

    @Override
    @GetMapping("/readAll")
    public List<TypeDish> readAll() {
        return typeDishService.readAll();
    }

    @Override
    @GetMapping("/readById/{id}")
    public Optional<TypeDish> readById(@PathVariable Integer id) {
        String url = "http://localhost:3000/orders";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);

        Optional<TypeDish> typeDish = typeDishService.readById(id);

        return ResponseEntity.ok(typeDish).getBody();
    }

    @Override
    @GetMapping("/readByName/{name}")
    public ResponseEntity<TypeDish> readByName(@PathVariable String name) {
        return typeDishService.readByName(name);
    }

    @Override
    @PutMapping("/update/{id}")
    public ResponseEntity<TypeDish> update(@PathVariable Integer id,@RequestBody TypeDish typeDish) {
        return typeDishService.update(id, typeDish);
    }
}
