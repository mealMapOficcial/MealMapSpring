package com.riwi.MealMap.application.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

import com.riwi.MealMap.application.dtos.exception.GenericNotFoundExceptions;

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
        Drink drink = Drink.builder()
                .name(drinkDTO.getName())
                .price(drinkDTO.getPrice())
                .promotion(drinkDTO.isPromotion())
                .typeOfDrinks(drinkDTO.getTypeOfDrinks())
                .build();

        drink = this.drinkRepository.save(drink);

        List<IngredientsOnlyWithName> ingredientsRequest = drinkDTO.getIngredients();
        List<Ingredient> ingredients = new ArrayList<>();
        List<DrinksIngredients> drinksIngredientsList = new ArrayList<>();

        for (IngredientsOnlyWithName requestIngredient : ingredientsRequest) {
            Ingredient ingredient = this.ingredientRepository.findOneByName(requestIngredient.getName())
                    .orElseThrow(() -> new GenericNotFoundExceptions("Ingredient not found"));

            validateStock(ingredient, requestIngredient.getQuantity());

            DrinksIngredients drinksIngredients = DrinksIngredients.builder()
                    .ingredients(ingredient)
                    .quantity(requestIngredient.getQuantity())
                    .drinks(drink)
                    .build();

            drinksIngredientsList.add(drinksIngredients);
            ingredients.add(ingredient);
        }

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

    private void validateStock(Ingredient ingrediente, double quantity) {
        Optional<Stock> stock = Optional.ofNullable(this.stockRepository.findByIngredientId(ingrediente.getId()));
        if (stock.isPresent()) {
            if (stock.get().getAmount() < quantity) {
                throw new GenericNotFoundExceptions("Not enough to create that drink: " + ingrediente.getName());
            }
        } else {
            throw new GenericNotFoundExceptions("Stock not found for ingredient: " + ingrediente.getName());
        }
    }

    @Override
    public void delete(Integer id) {
        try {
            drinkRepository.deleteById(id);
        } catch (Exception ex) {
            logger.error("Error deleting drink with id {}: {}", id, ex.getMessage());
            throw new GenericNotFoundExceptions("Drink not found with id: " + id);
        }
    }

    @Override
    public List<Drink> readAll() {
        return drinkRepository.findAll();
    }

    @Override
    public Optional<Drink> readById(Integer id) {
        return drinkRepository.findById(id)
                .map(Optional::of)
                .orElseThrow(() -> new GenericNotFoundExceptions("Drink not found with id: " + id));
    }

    @Override
    public ResponseEntity<Drink> readByName(String name) {
        Optional<Drink> drink = drinkRepository.findByName(name);

        if (drink.isPresent()) {
            return ResponseEntity.ok(drink.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Drink> updateDTO(Integer id, DrinkUpdateDTO drinkDTO) {
        Drink existingDrink = drinkRepository.findById(id)
                .orElseThrow(() -> new GenericNotFoundExceptions("Drink not found"));

        existingDrink.setName(drinkDTO.getName());
        existingDrink.setPrice(drinkDTO.getPrice());
        existingDrink.setPromotion(drinkDTO.isPromotion());
        existingDrink.setTypeOfDrinks(drinkDTO.getTypeOfDrinks());

        List<DrinksIngredients> drinksIngredientsList = new ArrayList<>();

        for (IngredientUpdateDTO requestIngredient : drinkDTO.getIngredients()) {
            Ingredient ingredient = ingredientRepository.findOneByName(requestIngredient.getName())
                    .orElseThrow(() -> new GenericNotFoundExceptions("Ingredient not found"));

            DrinksIngredients drinksIngredients = new DrinksIngredients();
            drinksIngredients.setIngredients(ingredient);

            drinksIngredientsList.add(drinksIngredients);
        }

        List<Ingredient> ingredientList = drinksIngredientsList.stream()
                .map(DrinksIngredients::getIngredients)
                .collect(Collectors.toList());

        existingDrink.setIngredients(ingredientList);

        Drink savedDrink = drinkRepository.save(existingDrink);

        return ResponseEntity.ok(savedDrink);
    }
}
