package com.riwi.MealMap.application.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.riwi.MealMap.application.dtos.request.DishWithoutId;
import com.riwi.MealMap.application.dtos.request.DishWithoutIdAndWithDTO;
import com.riwi.MealMap.application.dtos.request.IngredientsOnlyWithName;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.riwi.MealMap.application.dtos.exception.GenericNotFoundExceptions;
import com.riwi.MealMap.domain.entities.Dish;
import com.riwi.MealMap.domain.entities.DishesIngredients;
import com.riwi.MealMap.domain.entities.Ingredient;
import com.riwi.MealMap.domain.entities.Stock;
import com.riwi.MealMap.domain.ports.service.IDishService;
import com.riwi.MealMap.infrastructure.persistence.DishIngredientRepository;
import com.riwi.MealMap.infrastructure.persistence.DishRepository;
import com.riwi.MealMap.infrastructure.persistence.IngredientRepository;
import com.riwi.MealMap.infrastructure.persistence.StockRepository;

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
public DishWithoutId createGeneric(DishWithoutId dishDTO) {

    Dish dish = Dish.builder()
            .name(dishDTO.getName())
            .price(dishDTO.getPrice())
            .promotion(dishDTO.isPromotion())
            .typeOfDishes(dishDTO.getTypeOfDishes())
            .build(); 

   
    dish = this.dishRepository.save(dish);
   

    List<IngredientsOnlyWithName> ingredientsRequest = dishDTO.getIngredients();
    List<Ingredient> ingredients = new ArrayList<>();
    List<DishesIngredients> dishesIngredientsList = new ArrayList<>(); 


    for (IngredientsOnlyWithName requestIngredient : ingredientsRequest) {

        Ingredient ingredient = this.ingredientRepository.findOneByName(requestIngredient.getName())
                .orElseThrow(() -> new GenericNotFoundExceptions("Ingredient not found"));

        
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
        
        List<Dish> dishesEntity = this.dishRepository.findDishesWithIngredients();

        return dishesEntity.stream()
        .map(dish-> {
            DishWithoutIdAndWithDTO dishWithoutIdAndWithDTO = DishWithoutIdAndWithDTO.builder()
            .name(dish.getName())
            .price(dish.getPrice())
            .promotion(dish.isPromotion())
            .typeOfDishes(dish.getTypeOfDishes())
            .build();

            List<IngredientsOnlyWithName> ingredients = dish.getIngredients().stream()
            .map(ingredient -> {
                IngredientsOnlyWithName ingredientsWithoutId = IngredientsOnlyWithName.builder()
                .name(ingredient.getName())
                .build();

                return ingredientsWithoutId;
            })

            .collect(Collectors.toList());

            dishWithoutIdAndWithDTO.setIngredients(ingredients);
            return dishWithoutIdAndWithDTO;
        })

        .collect(Collectors.toList());
    }
}




