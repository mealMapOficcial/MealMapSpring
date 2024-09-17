package com.riwi.MealMap.services.impl;

import com.riwi.MealMap.dtos.request.Ingredient.DishWithoutId;
import com.riwi.MealMap.entities.Dish;
import com.riwi.MealMap.entities.Ingredient;
import com.riwi.MealMap.entities.TypeDish;
import com.riwi.MealMap.interfaces.DishRepository;
import com.riwi.MealMap.interfaces.TypeDishRepository;
import com.riwi.MealMap.services.interfaces.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DishService implements IDishService {

    @Autowired
    DishRepository dishRepository;

    @Autowired
    TypeDishService typeDishService;

    @Autowired
    IngredientService ingredientService;

    @Override
    public ResponseEntity<Dish> createDTO(DishWithoutId dishDTO) {

        Optional<TypeDish> optionalTypeDish = typeDishService.readById(dishDTO.getTypeDishId());
        if (!optionalTypeDish.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(null); // Opcional: incluir un mensaje de error más específico
        }
        TypeDish typeDish = optionalTypeDish.get();

        // Obtener los objetos Ingredient a partir de la lista de IDs proporcionados en el DTO
        List<Ingredient> ingredients = new ArrayList<>();
        if (dishDTO.getIngredientIds() != null && !dishDTO.getIngredientIds().isEmpty()) {
            for (Integer ingredientId : dishDTO.getIngredientIds()) {
                Optional<Ingredient> optionalIngredient = ingredientService.readById(ingredientId);
                if (!optionalIngredient.isPresent()) {
                    return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(null); // Opcional: incluir un mensaje de error más específico
                }
                ingredients.add(optionalIngredient.get());
            }
        }

        // Crear el plato con el tipo y los ingredientes obtenidos
        Dish dish = Dish.builder()
                .name(dishDTO.getName())
                .price(dishDTO.getPrice())
                .typeDish(typeDish) // Usa el objeto TypeDish completo
                //.ingredients(ingredients) // Usa la lista de ingredientes
                .build();

        // Guardar el plato y devolver la respuesta
        Dish savedDish = dishRepository.save(dish);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedDish);
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
            existingDish.setTypeDish(dish.getTypeDish());
            existingDish.setIngredients(dish.getIngredients());

            Dish savedDish = dishRepository.save(existingDish);
            return ResponseEntity.ok(savedDish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
