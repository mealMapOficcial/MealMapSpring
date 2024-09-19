package com.riwi.MealMap.application.services.impl;

import com.riwi.MealMap.application.dtos.request.Ingredient.DishWithoutId;
import com.riwi.MealMap.application.dtos.request.Ingredient.DishWithoutIdAndWithDTO;
import com.riwi.MealMap.application.dtos.request.Ingredient.IngredientsOnlyWithName;
import com.riwi.MealMap.application.dtos.request.Ingredient.IngredientsWithoutId;
import com.riwi.MealMap.domain.entities.Dish;
import com.riwi.MealMap.domain.entities.DishesIngredients;
import com.riwi.MealMap.domain.entities.Ingredient;
import com.riwi.MealMap.domain.entities.Stock;
import com.riwi.MealMap.infrastructure.persistence.DishIngredientRepository;
import com.riwi.MealMap.infrastructure.persistence.StockRepository;
import com.riwi.MealMap.infrastructure.persistence.DishRepository;
import com.riwi.MealMap.domain.ports.service.IDishService;
import com.riwi.MealMap.infrastructure.persistence.IngredientRepository;
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
public class DishService implements IDishService {

    @Autowired
    DishIngredientRepository dishIngredientRepository;

    @Autowired
    IngredientRepository ingredientRepository;

    @Autowired
    DishRepository dishRepository;

    @Autowired
    StockRepository stockRepository;

    @Override
    public Dish createDTO(DishWithoutId dishDTO) {

        List<IngredientsOnlyWithName> ingredientsRequest = dishDTO.getIngredients();
        List<Ingredient> ingredientsList = new ArrayList<>();

        for (IngredientsOnlyWithName requestIngredient : ingredientsRequest) {

            Optional<Ingredient> optionalIngredient = this.ingredientRepository.findOneByName(requestIngredient.getName());

            if(optionalIngredient.isPresent()){
                validateStock(optionalIngredient.get(), requestIngredient.getQuantity());
                ingredientsList.add(optionalIngredient.get());
            } else {
                throw new RuntimeException(("ingredient no exist"));
            }

        }


        Dish dish = Dish.builder()
                .name(dishDTO.getName())
                .price(dishDTO.getPrice())
                .promotion(dishDTO.isPromotion())
                .typeOfDishes(dishDTO.getTypeOfDishes())
                .ingredients(ingredientsList)
                .build();

        Dish savedDish = dishRepository.save(dish);



        return dish;
    }


    @Override
    public void delete(Integer id) {
        dishRepository.deleteById(id);
    }

    @Override
    public List<Dish> readAll() {
        return dishRepository.findAll();
    }

    @Override
    public Optional<Dish> readById(Integer id) {
        return dishRepository.findById(id);
    }

    @Override
    public ResponseEntity<Dish> readByName(String name) {

        Optional<Dish> dish = dishRepository.findByName(name);

        if (dish.isPresent()) {
            return ResponseEntity.ok(dish.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Dish> update(Integer id, Dish dish) {
        Dish existingDish = dishRepository.findById(id).orElse(null);

        if (existingDish != null) {

            existingDish.setName(dish.getName());
            existingDish.setPrice(dish.getPrice());
            existingDish.setTypeOfDishes(dish.getTypeOfDishes());
            existingDish.setIngredients(dish.getIngredients());

            Dish savedDish = dishRepository.save(existingDish);
            return ResponseEntity.ok(savedDish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    private  void validateStock(Ingredient ingrediente, float quantity){
        Optional<Stock> stock = Optional.ofNullable(this.stockRepository.findByIngredientId(ingrediente.getId()));
        if(stock.isPresent()){
            if(stock.get().getAmount() < quantity){
                throw new RuntimeException(("Not enought to create that dish" + ingrediente.getName()));
            }
        } else {
            throw new RuntimeException(("Stock not found for ingredient" + ingrediente.getName()));
        }
    }

    private boolean isAvailable(Dish dish) {
        return dish.getIngredients().stream()
                .allMatch(ingredients -> {
                    Optional<DishesIngredients> dishesIngredients =
                            this.dishIngredientRepository.findByIngredientsIdAndDishesId(ingredients.getId(),
                                    dish.getId());
                    if (dishesIngredients.isEmpty()) {

                        return false;
                    }
                    double getQuantity = dishesIngredients.get().getQuantity();

                    Stock stock = this.stockRepository.findByIngredientId(ingredients.getId());
                    if (stock == null) {
                        return false;
                    }
                    return stock.getAmount() >= getQuantity;
                });
    }

    @Transactional(readOnly = true)
    @Override
    public List<DishWithoutIdAndWithDTO> getAvailableDish() {
        List<DishWithoutIdAndWithDTO> availableDish = new ArrayList<>();
        List<Dish> dishesEntity = this.dishRepository.findDishesWithIngredients();

        for (Dish dish : dishesEntity) {
            if (isAvailable(dish)) {
                DishWithoutIdAndWithDTO dishResponse = DishWithoutIdAndWithDTO.builder()
                        .name(dish.getName())
                        .price(dish.getPrice())
                        .typeOfDishes(dish.getTypeOfDishes())
                        .promotion(dish.isPromotion())
                        .ingredients(dish.getIngredients() != null ? dish.getIngredients().stream()
                                .map(ingredient -> IngredientsWithoutId.builder()
                                        .name(ingredient.getName())
                                        .price(ingredient.getPrice())
                                        .measure(ingredient.getMeasure())
                                        .build())
                                .collect(Collectors.toList()) : new ArrayList<>())
                        .build();

                availableDish.add(dishResponse);
            }
        }

        return availableDish;
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
