package com.riwi.MealMap.application.services.impl;

import com.riwi.MealMap.application.dtos.request.IngredientsWithoutId;
import com.riwi.MealMap.domain.entities.Ingredient;
import com.riwi.MealMap.domain.entities.Stock;
import com.riwi.MealMap.domain.ports.service.IIngredientService;
import com.riwi.MealMap.infrastructure.persistence.IngredientRepository;
import com.riwi.MealMap.infrastructure.persistence.StockRepository;
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

    @Autowired
    StockRepository stockRepository;

    @Override
    public ResponseEntity<Ingredient> createDTO(IngredientsWithoutId ingredientDTO) {

        Ingredient ingredient = Ingredient.builder()
                .name(ingredientDTO.getName())
                .price(ingredientDTO.getPrice())
                .measure(ingredientDTO.getMeasure())
                .build();

        Ingredient savedIngredient = ingredientRepository.save(ingredient);

        Stock stock = Stock.builder()
                .ingredients(savedIngredient)
                .amount(100)
                .build();

        stockRepository.save(stock);

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
        Optional<Ingredient> ingredient = ingredientRepository.findOneByName(name);

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
            existingIngredient.setMeasure(ingredient.getMeasure());

            Ingredient savedIngredient = ingredientRepository.save(existingIngredient);
            return ResponseEntity.ok(savedIngredient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
