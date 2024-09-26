package com.riwi.MealMap.application.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.riwi.MealMap.application.dtos.exception.EntityAlreadyExistsException;
import com.riwi.MealMap.application.dtos.exception.GenericExceptions;
import com.riwi.MealMap.application.dtos.exception.InsufficientIngredientsException;
import com.riwi.MealMap.application.dtos.request.*;
import com.riwi.MealMap.domain.entities.*;
import com.riwi.MealMap.domain.ports.service.IDrinkService;
import com.riwi.MealMap.infrastructure.persistence.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DrinkService implements IDrinkService {

    private static final Logger logger = LoggerFactory.getLogger(DrinkService.class);

    @Autowired
    DrinkIngredientRepository drinkIngredientRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    StockRepository stockRepository;

    @Override
    public DrinkWithoutId createGeneric(DrinkWithoutId drinkDTO) {
        if (drinkRepository.findByName(drinkDTO.getName()).isPresent()) {
            throw new EntityAlreadyExistsException("Drink already exists with name: " + drinkDTO.getName());
        }

        List<IngredientsOnlyWithName> ingredientsRequest = drinkDTO.getIngredients();
        List<Ingredient> ingredients = new ArrayList<>();
        List<DrinksIngredients> drinksIngredientsList = new ArrayList<>();

        // Verifica la disponibilidad de todos los ingredientes antes de crear la bebida
        for (IngredientsOnlyWithName requestIngredient : ingredientsRequest) {
            Ingredient ingredient = this.ingredientRepository.findOneByName(requestIngredient.getName())
                    .orElseThrow(() -> new GenericExceptions("Ingredient not found: " + requestIngredient.getName()));

            validateStock(ingredient, requestIngredient.getQuantity());
            ingredients.add(ingredient);

            DrinksIngredients drinksIngredients = DrinksIngredients.builder()
                    .ingredients(ingredient)
                    .quantity(requestIngredient.getQuantity())
                    .build();
            drinksIngredientsList.add(drinksIngredients);
        }

        // Si todos los ingredientes son válidos, crea la bebida
        Drink drink = Drink.builder()
                .name(drinkDTO.getName())
                .price(drinkDTO.getPrice())
                .promotion(drinkDTO.isPromotion())
                .imageUrl(drinkDTO.getImageUrl())
                .typeOfDrinks(drinkDTO.getTypeOfDrinks())
                .build();

        drink = this.drinkRepository.save(drink);
        this.drinkIngredientRepository.saveAll(drinksIngredientsList);
        drink.setIngredients(ingredients);

        List<IngredientsOnlyWithName> ingredientsDrinks = ingredients.stream()
                .map(ingredient -> IngredientsOnlyWithName.builder()
                        .name(ingredient.getName())
                        .build())
                .collect(Collectors.toList());

        return DrinkWithoutId.builder()
                .name(drink.getName())
                .price(drink.getPrice())
                .promotion(drink.isPromotion())
                .typeOfDrinks(drink.getTypeOfDrinks())
                .ingredients(ingredientsDrinks)
                .build();
    }

    private void validateStock(Ingredient ingredient, double quantity) {
        Optional<Stock> stock = Optional.ofNullable(this.stockRepository.findByIngredientId(ingredient.getId()));
        if (stock.isPresent()) {
            if (stock.get().getQuantity() < quantity) {
                throw new InsufficientIngredientsException("There is not enough stock to create this drink: " + ingredient.getName());
            }
        } else {
            throw new GenericExceptions("Stock not found for ingredient: " + ingredient.getName());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            if (!drinkRepository.existsById(id)) {
                throw new GenericExceptions("Drink not found with id: " + id);
            }
            drinkRepository.deleteById(id);
        } catch (GenericExceptions ex) {
            logger.error("Error deleting drink: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            logger.error("Unexpected error during deletion: {}", ex.getMessage());
            throw new RuntimeException("Failed to delete drink: " + ex.getMessage());
        }
    }

    @Override
    public List<Drink> readAll() {
        try {
            return drinkRepository.findAll();
        } catch (Exception ex) {
            logger.error("Error retrieving drinks: {}", ex.getMessage());
            throw new RuntimeException("Failed to retrieve drinks: " + ex.getMessage());
        }
    }

    @Override
    public Optional<Drink> readById(Integer id) {
        return drinkRepository.findById(id)
                .map(Optional::of)
                .orElseThrow(() -> new GenericExceptions("Drink not found with id: " + id));
    }

    @Override
    public ResponseEntity<Drink> readByName(String name) {
        try {
            Optional<Drink> drink = drinkRepository.findByName(name);
            if (drink.isPresent()) {
                return ResponseEntity.ok(drink.get());
            } else {
                throw new GenericExceptions("Drink not found with name: " + name);
            }
        } catch (GenericExceptions ex) {
            logger.error("Error retrieving drink by name: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            logger.error("Unexpected error retrieving drink: {}", ex.getMessage());
            throw new RuntimeException("Failed to retrieve drink: " + ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<Drink> updateDTO(Integer id, DrinkUpdateDTO drinkDTO) {
        Drink existingDrink = drinkRepository.findById(id)
                .orElseThrow(() -> new GenericExceptions("Drink not found with id: " + id));

        if (drinkDTO.getName() != null) {
            existingDrink.setName(drinkDTO.getName());
        }
        if (drinkDTO.getPrice() != null) {
            existingDrink.setPrice(drinkDTO.getPrice());
        }
        if (drinkDTO.getPromotion() != null) {
            existingDrink.setPromotion(drinkDTO.getPromotion());
        }
        if (drinkDTO.getImageUrl() != null) {
            existingDrink.setImageUrl(drinkDTO.getImageUrl());
        }
        if (drinkDTO.getTypeOfDrinks() != null) {
            existingDrink.setTypeOfDrinks(drinkDTO.getTypeOfDrinks());
        }

        List<DrinksIngredients> drinksIngredientsList = new ArrayList<>();

        if (drinkDTO.getIngredients() != null && !drinkDTO.getIngredients().isEmpty()) {
            for (IngredientUpdateDTO requestIngredient : drinkDTO.getIngredients()) {
                Ingredient ingredient = ingredientRepository.findOneByName(requestIngredient.getName())
                        .orElseThrow(() -> new GenericExceptions("Ingredient not found: " + requestIngredient.getName()));

                DrinksIngredients drinksIngredients = new DrinksIngredients();
                drinksIngredients.setIngredients(ingredient);
                drinksIngredientsList.add(drinksIngredients);
            }
        }

        if (!drinksIngredientsList.isEmpty()) {
            existingDrink.setIngredients(drinksIngredientsList.stream()
                    .map(DrinksIngredients::getIngredients)
                    .collect(Collectors.toList()));
        }

        Drink savedDrink = drinkRepository.save(existingDrink);
        return ResponseEntity.ok(savedDrink);
    }
}
