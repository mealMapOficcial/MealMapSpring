package com.riwi.MealMapSpring.services.impl;

import com.riwi.MealMapSpring.Entities.Dishes;
import com.riwi.MealMapSpring.Entities.Ingredients;
import com.riwi.MealMapSpring.Entities.Stock;
import com.riwi.MealMapSpring.Repository.Interfaces.DishRepository;
import com.riwi.MealMapSpring.Repository.Interfaces.IngredientsRepository;
import com.riwi.MealMapSpring.Repository.Interfaces.StockRepository;
import com.riwi.MealMapSpring.dtos.Request.DishRequest;
import com.riwi.MealMapSpring.dtos.Request.RequestIngredients;
import com.riwi.MealMapSpring.dtos.Response.DishResponse;
import com.riwi.MealMapSpring.services.interfacesEntity.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DishService implements IDishService {
@Autowired
DishRepository dishRepository;
@Autowired
IngredientsRepository ingredientsRepository;

@Autowired
StockRepository stockRepository;
    @Override
    public DishResponse create(DishRequest request) {
        Dishes dishes = Dishes.builder()
                .name(request.getName())
                .price(request.getPrice())
                .typeOfDishes(request.getTypeOfDishes())
                .build();
        dishRepository.save(dishes);

        List<RequestIngredients> ingredientsRequest = request.getIngredients();
        List<Ingredients> ingredientsList = new ArrayList<>();
        for(RequestIngredients requestIngredients : ingredientsRequest){
            List<Ingredients> ingredients = this.ingredientsRepository.findByName(requestIngredients.getName());

            if (ingredients.isEmpty()) {
                throw new RuntimeException("The ingredient " + requestIngredients.getName() + " not exist.");
            }
            ingredientsList.add(ingredients.get(0));
        }

        if(hasEnoughStock(ingredientsList)){
            dishes.setIngredients(ingredientsList);
            updateStock(ingredientsList);

            DishResponse dishResponse = DishResponse.builder()
                    .name(dishes.getName())
                    .typeOfDishes(dishes.getTypeOfDishes())
                    .price(dishes.getPrice())
                    .build();
            return dishResponse;
        } else {
     throw  new RuntimeException("Not enough stock to create that dish ");
        }


    }


    private void updateStock(List<Ingredients> ingredientsList) {
        ingredientsList.forEach(ingredient -> {
            Long Idingredients = ingredient.getId();
            Stock stock = stockRepository.findByIngredientId(Idingredients);
            if (stock != null) {
                stock.setAmount(stock.getAmount() - 1);
                stockRepository.save(stock);
            }
        });
    }

    private boolean hasEnoughStock(List<Ingredients> ingredientsList){
        for(Ingredients ingredients: ingredientsList){
            Stock stock = this.stockRepository.findByIngredientId(ingredients.getId());
            if(stock == null || stock.getAmount() == 0){
                return false;
            }
        }
        return  true;
    }

    private boolean isAvailable(Dishes dishes){
       return dishes.getIngredients().stream()
               .allMatch(ingredients -> {
                   Stock stock = this.stockRepository.findByIngredientId(ingredients.getId());
                   return stock != null || stock.getAmount() > 0;
               });
    }
@Override
    public List<DishResponse>  getAvailableDish(){
        List<DishResponse> availableDish = new ArrayList<>();
        List<Dishes> dishesEntity = this.dishRepository.findAll();
        for(Dishes dishes : dishesEntity){
            if(isAvailable(dishes)){
                DishResponse dishResponse = DishResponse.builder()
                        .name(dishes.getName())
                        .price(dishes.getPrice())
                        .typeOfDishes(dishes.getTypeOfDishes())
                        .build();
                availableDish.add(dishResponse);
            }
        }
      return availableDish;
    }
}
