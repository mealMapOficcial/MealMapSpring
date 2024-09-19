package com.riwi.MealMap.application.services.impl;

import com.riwi.MealMap.application.dtos.request.DrinkRequest;
import com.riwi.MealMap.application.dtos.request.DrinkWithoutId;
import com.riwi.MealMap.application.dtos.request.IngredientsOnlyWithName;
import com.riwi.MealMap.application.dtos.request.IngredientsWithoutId;
import com.riwi.MealMap.domain.entities.*;
import com.riwi.MealMap.domain.ports.service.IDrinkService;
import com.riwi.MealMap.infrastructure.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DrinkService implements IDrinkService {

    @Autowired
    DrinkIngredientRepository drinkIngredientRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    StockRepository stockRepository;

    @Override
    public ResponseEntity<Drink> createDTO(DrinkWithoutId drinkDTO) {

        List<IngredientsOnlyWithName> ingredientsRequest = drinkDTO.getIngredients();
        List<Ingredient> ingredientsList = new ArrayList<>();

        for (IngredientsOnlyWithName requestIngredient : ingredientsRequest) {

            Optional<Ingredient> optionalIngredient = this.ingredientRepository.findOneByName(requestIngredient.getName());

            Ingredient ingredient = optionalIngredient.orElseThrow(() ->
                    new RuntimeException("El ingrediente " + requestIngredient.getName() + " no existe."));

            ingredientsList.add(ingredient);
        }

        if (!hasEnoughStock(ingredientsList)) {
            throw new RuntimeException("No tienes los ingredientes suficientes para crear este plato.");
        }

        Drink drink = Drink.builder()
                .name(drinkDTO.getName())
                .price(drinkDTO.getPrice())
                .promotion(drinkDTO.isPromotion())
                .typeOfDrinks(drinkDTO.getTypeOfDrinks())
                .ingredients(ingredientsList)
                .build();

        Drink savedDrink = drinkRepository.save(drink);

        updateStock(ingredientsList);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedDrink);
    }


    @Override
    public void delete(Integer id) {
        drinkRepository.deleteById(id);
    }

    @Override
    public List<Drink> readAll() {
        return drinkRepository.findAll();
    }

    @Override
    public Optional<Drink> readById(Integer id) {
        return drinkRepository.findById(id);
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
    public ResponseEntity<Drink> update(Integer id, Drink drink) {
        Drink existingDrink = drinkRepository.findById(id).orElse(null);

        if (existingDrink != null) {

            existingDrink.setName(drink.getName());
            existingDrink.setPrice(drink.getPrice());
            existingDrink.setTypeOfDrinks(drink.getTypeOfDrinks());
            existingDrink.setIngredients(drink.getIngredients());

            Drink savedDrink = drinkRepository.save(existingDrink);
            return ResponseEntity.ok(savedDrink);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private boolean isAvailable(Drink drink) {
        return drink.getIngredients().stream()
                .allMatch(ingredient -> {
                    Optional<DrinksIngredients> drinksIngredients =
                            this.drinkIngredientRepository.findByIngredientsIdAndDrinksId(ingredient.getId(), drink.getId());
                    if (drinksIngredients.isEmpty()) {
                        return false;
                    }

                    Long quantity = drinksIngredients.get().getQuantity(); // Cambiado a Long
                    if (quantity == null) {
                        return false;
                    }

                    Stock stock = this.stockRepository.findByIngredientId(ingredient.getId());
                    if (stock == null) {
                        return false;
                    }

                    return stock.getAmount() >= quantity; // Asegúrate de que stock.getAmount() también sea Long
                });
    }

    @Transactional(readOnly = true)
    @Override
    public List<DrinkRequest> getAvailableDrink() {
        List<DrinkRequest> availableDrink = new ArrayList<>();
        List<Drink> drinksEntity = this.drinkRepository.findDrinksWithIngredients();

        for (Drink drink : drinksEntity) {
            if (isAvailable(drink)) {
                DrinkRequest drinkResponse = DrinkRequest.builder()
                        .name(drink.getName())
                        .price(drink.getPrice())
                        .typeOfDrinks(drink.getTypeOfDrinks())
                        .promotion(drink.isPromotion())
                        .ingredients(drink.getIngredients() != null ? drink.getIngredients().stream()
                                .map(ingredient -> IngredientsWithoutId.builder()
                                        .name(ingredient.getName())
                                        .price(ingredient.getPrice())
                                        .measure(ingredient.getMeasure())
                                        .build())
                                .collect(Collectors.toList()) : new ArrayList<>())
                        .build();

                availableDrink.add(drinkResponse);
            }
        }

        return availableDrink;
    }

    private void updateStock(List<Ingredient> ingredientsList) {
        ingredientsList.forEach(ingredient -> {
            Integer Idingredients = ingredient.getId();
            Stock stock = stockRepository.findByIngredientId(Idingredients);
            if (stock != null) {
                stock.setAmount(stock.getAmount() - 1);
                stockRepository.save(stock);
            }
        });
    }

    private boolean hasEnoughStock(List<Ingredient> ingredientsList) {
        for (Ingredient ingredients : ingredientsList) {
            Stock stock = this.stockRepository.findByIngredientId(ingredients.getId());
            if (stock == null || stock.getAmount() == 0) {
                return false;
            }
        }
        return true;
    }
}
