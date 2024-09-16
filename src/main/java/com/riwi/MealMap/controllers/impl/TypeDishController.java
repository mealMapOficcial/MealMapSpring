package com.riwi.MealMap.controllers.impl;

import com.riwi.MealMap.dtos.request.Ingredient.TypeDishWithoutId;
import com.riwi.MealMap.entities.Dish;
import com.riwi.MealMap.entities.TypeDish;
import com.riwi.MealMap.services.impl.DishService;
import com.riwi.MealMap.services.impl.TypeDishService;
import com.riwi.MealMap.services.interfaces.ITypeDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
    public ResponseEntity<TypeDish> createDTO(TypeDishWithoutId typeDish) {
        return typeDishService.createDTO(typeDish);
    }

    @Override
    public void delete(@PathVariable Integer id) {
        typeDishService.delete(id);
    }

    @Override
    public List<TypeDish> readAll() {
        return typeDishService.readAll();
    }

    @Override
    public Optional<TypeDish> readById(@PathVariable Integer id) {
        String url = "http://localhost:3000/orders";
        String response = restTemplate.getForObject(url, String.class);
        System.out.println(response);

        Optional<TypeDish> typeDish = typeDishService.readById(id);

        return ResponseEntity.ok(typeDish).getBody();
    }

    @Override
    public ResponseEntity<TypeDish> readByName(@PathVariable String name) {
        return typeDishService.readByName(name);
    }

    @Override
    public ResponseEntity<TypeDish> update(@PathVariable Integer id,@RequestBody TypeDish typeDish) {
        return typeDishService.update(id, typeDish);
    }
}
