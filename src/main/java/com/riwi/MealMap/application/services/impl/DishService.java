package com.riwi.MealMap.application.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.riwi.MealMap.application.dtos.request.*;
import com.riwi.MealMap.domain.entities.*;
import com.riwi.MealMap.application.dtos.exception.GenericNotFoundExceptions;
import com.riwi.MealMap.application.dtos.exception.IngredientNotFoundException;
import com.riwi.MealMap.application.dtos.exception.InsufficientIngredientsException;
import com.riwi.MealMap.application.dtos.exception.StockNotFoundException;

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
import org.springframework.transaction.annotation.Transactional;

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
        try {
            Dish dish = Dish.builder()
                    .name(dishDTO.getName())
                    .price(dishDTO.getPrice())
                    .promotion(dishDTO.isPromotion())
                    .imageUrl(dishDTO.getImageUrl())
                    .typeOfDishes(dishDTO.getTypeOfDishes())
                    .build();

            dish = this.dishRepository.save(dish);
            List<IngredientsOnlyWithName> ingredientsRequest = dishDTO.getIngredients();
            List<Ingredient> ingredients = new ArrayList<>();
            List<DishesIngredients> dishesIngredientsList = new ArrayList<>();

            for (IngredientsOnlyWithName requestIngredient : ingredientsRequest) {
                Ingredient ingredient = this.ingredientRepository.findOneByName(requestIngredient.getName())
                        .orElseThrow(() -> new IngredientNotFoundException("Ingredient not found: " + requestIngredient.getName()));

                validateStock(ingredient, requestIngredient.getQuantity());

                DishesIngredients dishesIngredients = DishesIngredients.builder()
                        .ingredients(ingredient)
                        .quantity(requestIngredient.getQuantity())
                        .dishes(dish)
                        .build();

                dishesIngredientsList.add(dishesIngredients);
                ingredients.add(ingredient);
            }

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
        } catch (IngredientNotFoundException | InsufficientIngredientsException ex) {
            logger.error("Error creating dish: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            logger.error("Unexpected error: {}", ex.getMessage());
            throw new RuntimeException("Failed to create dish: " + ex.getMessage());
        }
    }

    private void validateStock(Ingredient ingredient, double quantity) {
        Optional<Stock> stock = Optional.ofNullable(this.stockRepository.findByIngredientId(ingredient.getId()));
        if (stock.isPresent()) {
            if (stock.get().getAmount() < quantity) {
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
                throw new GenericNotFoundExceptions("Dish not found with id: " + id);
            }
            dishRepository.deleteById(id);
        } catch (GenericNotFoundExceptions ex) {
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
                .orElseThrow(() -> new GenericNotFoundExceptions("Dish not found with id: " + id));
    }


    @Override
    public ResponseEntity<Dish> readByName(String name) {
        try {
            Optional<Dish> dish = dishRepository.findByName(name);
            if (dish.isPresent()) {
                return ResponseEntity.ok(dish.get());
            } else {
                throw new GenericNotFoundExceptions("Dish not found with name: " + name);
            }
        } catch (GenericNotFoundExceptions ex) {
            logger.error("Error retrieving dish by name: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            logger.error("Unexpected error retrieving dish: {}", ex.getMessage());
            throw new RuntimeException("Failed to retrieve dish: " + ex.getMessage());
        }
    }

    @Override
    public ResponseEntity<Dish> updateDTO(Integer id, DishUpdateDTO dishDTO) {
        try {
            Dish existingDish = dishRepository.findById(id)
                    .orElseThrow(() -> new GenericNotFoundExceptions("Dish not found with id: " + id));

            existingDish.setName(dishDTO.getName());
            existingDish.setPrice(dishDTO.getPrice());
            existingDish.setPromotion(dishDTO.isPromotion());
            existingDish.setImageUrl(dishDTO.getImageUrl());
            existingDish.setTypeOfDishes(dishDTO.getTypeOfDishes());

            List<DishesIngredients> dishesIngredientsList = new ArrayList<>();

            for (IngredientUpdateDTO requestIngredient : dishDTO.getIngredients()) {
                Ingredient ingredient = ingredientRepository.findOneByName(requestIngredient.getName())
                        .orElseThrow(() -> new IngredientNotFoundException("Ingredient not found: " + requestIngredient.getName()));

                DishesIngredients dishesIngredients = new DishesIngredients();
                dishesIngredients.setIngredients(ingredient);

                dishesIngredientsList.add(dishesIngredients);
            }

            List<Ingredient> ingredientList = dishesIngredientsList.stream()
                    .map(DishesIngredients::getIngredients)
                    .collect(Collectors.toList());

            existingDish.setIngredients(ingredientList);
            Dish savedDish = dishRepository.save(existingDish);
            return ResponseEntity.ok(savedDish);
        } catch (IngredientNotFoundException | GenericNotFoundExceptions ex) {
            logger.error("Error updating dish: {}", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            logger.error("Unexpected error during update: {}", ex.getMessage());
            throw new RuntimeException("Failed to update dish: " + ex.getMessage());
        }
    }
}
