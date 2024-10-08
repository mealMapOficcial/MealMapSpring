package com.riwi.MealMap.application.services.impl;

import com.riwi.MealMap.application.dtos.exception.EntityAlreadyExistsException;
import com.riwi.MealMap.application.dtos.exception.GenericExceptions;
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

        if (ingredientRepository.findOneByName(ingredientDTO.getName()).isPresent()) {
            throw new EntityAlreadyExistsException("Ingredient already exists with name: " + ingredientDTO.getName());
        }

        Ingredient ingredient = Ingredient.builder()
                .name(ingredientDTO.getName())
                .price(ingredientDTO.getPrice())
                .measure(ingredientDTO.getMeasure())
                .quantity(ingredientDTO.getQuantity())
                .build();

        Ingredient savedIngredient = ingredientRepository.save(ingredient);

        Stock stock = Stock.builder()
                .ingredients(savedIngredient)
                .quantity(ingredientDTO.getQuantity())
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

    public ResponseEntity<Ingredient> update(Integer id, Ingredient ingredient) {
        Ingredient existingIngredient = ingredientRepository.findById(id).orElse(null);

        if (existingIngredient != null) {
            existingIngredient.setName(ingredient.getName());
            existingIngredient.setPrice(ingredient.getPrice());
            existingIngredient.setMeasure(ingredient.getMeasure());

            Stock existingStock = stockRepository.findByIngredientId(existingIngredient.getId());
            if (existingStock != null) {
                existingStock.setQuantity(ingredient.getQuantity());
                stockRepository.save(existingStock);
            } else {
                throw new GenericExceptions("Stock not found for ingredient: " + existingIngredient.getName());
            }

            Ingredient savedIngredient = ingredientRepository.save(existingIngredient);
            return ResponseEntity.ok(savedIngredient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


    public void validateStock(Ingredient ingredient, double quantity) {
        Optional<Stock> stock = Optional.ofNullable(stockRepository.findByIngredientId(ingredient.getId()));
        if (stock.isPresent()) {
            if (stock.get().getQuantity() < quantity) {
                throw new GenericExceptions("Not enough stock for ingredient: " + ingredient.getName());
            }
        } else {
            throw new GenericExceptions("Stock not found for ingredient: " + ingredient.getName());
        }
    }

}
