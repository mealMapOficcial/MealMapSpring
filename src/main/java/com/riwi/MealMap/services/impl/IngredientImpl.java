package com.riwi.MealMap.services.impl;

import com.riwi.MealMap.dtos.request.Ingredient.IngredientsWithoutId;
import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.interfaces.IngredientRepository;
import com.riwi.MealMap.services.interfaces.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientImpl implements IIngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    @PostMapping()
    public ResponseEntity<Ingredient> create(IngredientsWithoutId ingredientDTO) {
        Ingredient ingredient = Ingredient.builder()
                .name(ingredientDTO.getName())
                .price(ingredientDTO.getPrice())
                .weight(ingredientDTO.getWeight())
                .build();

        Ingredient savedIngredient = ingredientRepository.save(ingredient);

        return ResponseEntity.ok(savedIngredient);
    }

    @Override
    public List<Ingredient> readAll() {
        return List.of();
    }

    @Override
    public ResponseEntity<Ingredient> readByName(String name) {

        Optional<Ingredient> ingredient = ingredientRepository.findByName(name);

        if (ingredient.isPresent()) {
            return ResponseEntity.ok(ingredient.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public void delete(Integer integer) {

    }

    @Override
    public ResponseEntity<Ingredient> update(Integer id, Ingredient ingredient) {
        return null;
    }
}
