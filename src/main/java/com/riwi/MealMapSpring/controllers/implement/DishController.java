package com.riwi.MealMapSpring.controllers.implement;

import com.riwi.MealMapSpring.controllers.InterfaceControllerEntity.IDishController;
import com.riwi.MealMapSpring.dtos.Request.DishRequest;
import com.riwi.MealMapSpring.dtos.Response.DishResponse;
import com.riwi.MealMapSpring.services.interfacesEntity.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import com.riwi.MealMapSpring.dtos.exception.ErrorResponse;

import java.util.List;

@RequestMapping("/mealmap")
@RestController
public class DishController implements IDishController {

    @Autowired
    IDishService dishService;

    @PostMapping("/add")
    @Override
    public ResponseEntity<DishResponse> create(@RequestBody DishRequest dishRequest) {
        DishResponse dishResponse = this.dishService.create(dishRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(dishResponse);
    }

//     http://localhost:8080/mealmap/available
    @Override
    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public List<DishResponse> getAvailableDish() {
        try{
            return this.dishService.getAvailableDish();
        } catch (Exception e){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "ERROR FOUND DISHES");
        }

    }

}
