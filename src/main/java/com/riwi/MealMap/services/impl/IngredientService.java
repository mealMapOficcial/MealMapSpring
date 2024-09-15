package com.riwi.MealMap.services.impl;

import com.riwi.MealMap.dtos.request.Ingredient.IngredientsWithoutId;
import com.riwi.MealMap.entities.Dish;
import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.interfaces.IngredientRepository;
import com.riwi.MealMap.services.interfaces.IIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngredientService implements IIngredientService {

    @Autowired
    IngredientRepository ingredientRepository;

    @Override
    public ResponseEntity<Ingredient> createDTO(IngredientsWithoutId ingredientDTO) {

        Ingredient ingredient = Ingredient.builder()
                .name(ingredientDTO.getName())
                .price(ingredientDTO.getPrice())
                .weight(ingredientDTO.getWeight())
                .build();

        Ingredient savedIngredient = ingredientRepository.save(ingredient);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedIngredient);
    }

    @Override
    public List<Ingredient> readAll() {
        return ingredientRepository.findAll();
    }

    @Override
    public Optional<Ingredient> readById(Integer id) {
        return ingredientRepository.findById(id);
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
    public void delete(Integer id) {
        ingredientRepository.deleteById(id);
    }

    @Override
    public ResponseEntity<Ingredient> update(Integer id, Ingredient ingredient) {
        Ingredient existingIngredient = ingredientRepository.findById(id).orElse(null);

        if (existingIngredient != null) {

            existingIngredient.setName(ingredient.getName());
            existingIngredient.setPrice(ingredient.getPrice());
            existingIngredient.setWeight(ingredient.getWeight());

            Ingredient savedIngredient = ingredientRepository.save(existingIngredient);
            return ResponseEntity.ok(savedIngredient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
