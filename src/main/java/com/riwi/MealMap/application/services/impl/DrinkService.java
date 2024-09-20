package com.riwi.MealMap.application.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.riwi.MealMap.application.dtos.request.DrinkWithoutId;
import com.riwi.MealMap.application.dtos.request.DrinkWithoutIdAndWithDTO;
import com.riwi.MealMap.application.dtos.request.IngredientsOnlyWithName;
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
import com.riwi.MealMap.domain.ports.service.IDishService;

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
                        .measure(ingredient.getMeasure())
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

    private  void validateStock(Ingredient ingrediente, double quantity){
        Optional<Stock> stock = Optional.ofNullable(this.stockRepository.findByIngredientId(ingrediente.getId()));
        if(stock.isPresent()){
            if(stock.get().getAmount() < quantity){
                throw new GenericNotFoundExceptions(("Not enought to create that dish" + ingrediente.getName()));
            }
        } else {
            throw new GenericNotFoundExceptions(("Stock not found for ingredient" + ingrediente.getName()));
        }
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
                .allMatch(ingredients -> {
                    Optional<DrinksIngredients> drinksIngredients =
                            this.drinkIngredientRepository.findByIngredientsIdAndDrinksId(ingredients.getId(),
                                    drink.getId());
                    if (drinksIngredients.isEmpty()) {

                        return false;
                    }
                    double getQuantity = drinksIngredients.get().getQuantity();

                    Stock stock = this.stockRepository.findByIngredientId(ingredients.getId());
                    if (stock == null) {
                        return false;
                    }
                    return stock.getAmount() >= getQuantity;
                });
    }





    @Transactional(readOnly = true)
    @Override
    public List<DrinkWithoutIdAndWithDTO> getAvailableDrink() {

        List<Drink> drinksEntity = this.drinkRepository.findDrinksWithIngredients();

        return drinksEntity.stream()
                .map(drink-> {
                    DrinkWithoutIdAndWithDTO drinkWithoutIdAndWithDTO = DrinkWithoutIdAndWithDTO.builder()
                            .name(drink.getName())
                            .price(drink.getPrice())
                            .promotion(drink.isPromotion())
                            .typeOfDrinks(drink.getTypeOfDrinks())
                            .build();

                    List<IngredientsOnlyWithName> ingredients = drink.getIngredients().stream()
                            .map(ingredient -> {
                                IngredientsOnlyWithName ingredientsWithoutId = IngredientsOnlyWithName.builder()
                                        .name(ingredient.getName())
                                        .measure(ingredient.getMeasure())
                                        .build();

                                return ingredientsWithoutId;
                            })

                            .collect(Collectors.toList());

                    drinkWithoutIdAndWithDTO.setIngredients(ingredients);
                    return drinkWithoutIdAndWithDTO;
                })

                .collect(Collectors.toList());
    }
}




