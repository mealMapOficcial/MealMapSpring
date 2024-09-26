package com.riwi.MealMap.application.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.riwi.MealMap.application.dtos.exception.*;
import com.riwi.MealMap.application.dtos.request.*;
import com.riwi.MealMap.domain.entities.*;

import com.riwi.MealMap.domain.ports.service.IDishService;
import com.riwi.MealMap.infrastructure.persistence.DishIngredientRepository;
import com.riwi.MealMap.infrastructure.persistence.DishRepository;
import com.riwi.MealMap.infrastructure.persistence.IngredientRepository;
import com.riwi.MealMap.infrastructure.persistence.StockRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class DishService implements IDishService {

    private static final Logger logger = LoggerFactory.getLogger(DishService.class);

    @Autowired
    DishIngredientRepository dishIngredientRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    DishRepository dishRepository;

    @Autowired
    StockRepository stockRepository;

    @Override
    public DishWithoutId createGeneric(DishWithoutId dishDTO) {
        if (dishRepository.findByName(dishDTO.getName()).isPresent()) {
            throw new EntityAlreadyExistsException("Dish already exists with name: " + dishDTO.getName());
        }

        List<IngredientsOnlyWithName> ingredientsRequest = dishDTO.getIngredients();
        List<Ingredient> ingredients = new ArrayList<>();
        List<DishesIngredients> dishesIngredientsList = new ArrayList<>();

        for (IngredientsOnlyWithName requestIngredient : ingredientsRequest) {
            Ingredient ingredient = this.ingredientRepository.findOneByName(requestIngredient.getName())
                    .orElseThrow(() -> new IngredientNotFoundException("Ingredient not found: " + requestIngredient.getName()));

            validateStock(ingredient, requestIngredient.getQuantity());
            ingredients.add(ingredient);

            DishesIngredients dishesIngredients = DishesIngredients.builder()
                    .ingredients(ingredient)
                    .quantity(requestIngredient.getQuantity())
                    .build();
            dishesIngredientsList.add(dishesIngredients);
        }

        Dish dish = Dish.builder()
                .name(dishDTO.getName())
                .price(dishDTO.getPrice())
                .promotion(dishDTO.isPromotion())
                .imageUrl(dishDTO.getImageUrl())
                .typeOfDishes(dishDTO.getTypeOfDishes())
                .build();

        dish = this.dishRepository.save(dish);
        this.dishIngredientRepository.saveAll(dishesIngredientsList);
        dish.setIngredients(ingredients);

        List<IngredientsOnlyWithName> ingredientsDish = ingredients.stream()
                .map(ingredient -> IngredientsOnlyWithName.builder()
                        .name(ingredient.getName())
                        .build())
                .collect(Collectors.toList());

        return DishWithoutId.builder()
                .name(dish.getName())
                .price(dish.getPrice())
                .promotion(dish.isPromotion())
                .typeOfDishes(dish.getTypeOfDishes())
                .ingredients(ingredientsDish)
                .build();
    }

    private void validateStock(Ingredient ingredient, double quantity) {
        Optional<Stock> stock = Optional.ofNullable(this.stockRepository.findByIngredientId(ingredient.getId()));
        if (stock.isPresent()) {
            if (stock.get().getQuantity() < quantity) {
                throw new InsufficientIngredientsException("Not enough to create that dish: " + ingredient.getName());
            }
        } else {
            throw new StockNotFoundException("Stock not found for ingredient: " + ingredient.getName());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            if (!dishRepository.existsById(id)) {
                throw new GenericExceptions("Dish not found with id: " + id);
            }
            dishRepository.deleteById(id);
        } catch (GenericExceptions ex) {
            logger.error("Error deleting dish: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            logger.error("Unexpected error during deletion: {}", ex.getMessage());
            throw new RuntimeException("Failed to delete dish: " + ex.getMessage());
        }
    }

    @Override
    public List<Dish> readAll() {
        try {
            return dishRepository.findAll();
        } catch (Exception ex) {
            logger.error("Error retrieving dishes: {}", ex.getMessage());
            throw new RuntimeException("Failed to retrieve dishes: " + ex.getMessage());
        }
    }

    @Override
    public Optional<Dish> readById(Integer id) {
        return dishRepository.findById(id)
                .map(Optional::of)
                .orElseThrow(() -> new GenericExceptions("Dish not found with id: " + id));
    }

    @Override
    public ResponseEntity<Dish> readByName(String name) {
        try {
            Optional<Dish> dish = dishRepository.findByName(name);
            if (dish.isPresent()) {
                return ResponseEntity.ok(dish.get());
            } else {
                throw new GenericExceptions("Dish not found with name: " + name);
            }
        } catch (GenericExceptions ex) {
            logger.error("Error retrieving dish by name: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            logger.error("Unexpected error retrieving dish: {}", ex.getMessage());
            throw new RuntimeException("Failed to retrieve dish: " + ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<Dish> updateDTO(Integer id, DishUpdateDTO dishDTO) {
        Dish existingDish = dishRepository.findById(id)
                .orElseThrow(() -> new GenericExceptions("Dish not found with id: " + id));

        if (dishDTO.getName() != null) {
            existingDish.setName(dishDTO.getName());
        }
        if (dishDTO.getPrice() != null) {
            existingDish.setPrice(dishDTO.getPrice());
        }
        if (dishDTO.getPromotion() != null) {
            existingDish.setPromotion(dishDTO.getPromotion());
        }
        if (dishDTO.getImageUrl() != null) {
            existingDish.setImageUrl(dishDTO.getImageUrl());
        }
        if (dishDTO.getTypeOfDishes() != null) {
            existingDish.setTypeOfDishes(dishDTO.getTypeOfDishes());
        }

        List<DishesIngredients> dishesIngredientsList = new ArrayList<>();

        if (dishDTO.getIngredients() != null && !dishDTO.getIngredients().isEmpty()) {
            for (IngredientUpdateDTO requestIngredient : dishDTO.getIngredients()) {
                Ingredient ingredient = ingredientRepository.findOneByName(requestIngredient.getName())
                        .orElseThrow(() -> new IngredientNotFoundException("Ingredient not found: " + requestIngredient.getName()));

                DishesIngredients dishesIngredients = new DishesIngredients();
                dishesIngredients.setIngredients(ingredient);
                dishesIngredientsList.add(dishesIngredients);
            }
        }

        if (!dishesIngredientsList.isEmpty()) {
            existingDish.setIngredients(dishesIngredientsList.stream()
                    .map(DishesIngredients::getIngredients)
                    .collect(Collectors.toList()));
        }

        Dish savedDish = dishRepository.save(existingDish);
        return ResponseEntity.ok(savedDish);
    }
}
