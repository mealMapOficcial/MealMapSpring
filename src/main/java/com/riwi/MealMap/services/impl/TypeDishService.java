package com.riwi.MealMap.services.impl;

import com.riwi.MealMap.dtos.request.Ingredient.TypeDishWithName;
import com.riwi.MealMap.entities.TypeDish;
import com.riwi.MealMap.interfaces.TypeDishRepository;
import com.riwi.MealMap.services.interfaces.ITypeDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TypeDishService implements ITypeDishService {

    @Autowired
    TypeDishRepository typeDishRepository;

    @Override
    public ResponseEntity<TypeDish> createDTO(TypeDishWithName typeDishDTO) {

        TypeDish typeDish = TypeDish.builder()
                .name(typeDishDTO.getName())
                .build();

        TypeDish savedTypeDish = typeDishRepository.save(typeDish);

        return ResponseEntity.status(HttpStatus.CREATED).body(savedTypeDish);
    }

    @Override
    public void delete(Integer id) {
        typeDishRepository.deleteById(id);
    }

    @Override
    public List<TypeDish> readAll() {
        return typeDishRepository.findAll();
    }

    @Override
    public Optional<TypeDish> readById(Integer id) {
        return typeDishRepository.findById(id);
    }

    @Override
    public ResponseEntity<TypeDish> readByName(String name) {

        Optional<TypeDish> typeDish = typeDishRepository.findByName(name);

        if (typeDish.isPresent()) {
            return ResponseEntity.ok(typeDish.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<TypeDish> update(Integer id, TypeDish typeDish) {
        TypeDish existingTypeDish = typeDishRepository.findById(id).orElse(null);

        if (existingTypeDish != null) {

            existingTypeDish.setName(typeDish.getName());

            TypeDish savedTypeDish = typeDishRepository.save(existingTypeDish);
            return ResponseEntity.ok(savedTypeDish);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
