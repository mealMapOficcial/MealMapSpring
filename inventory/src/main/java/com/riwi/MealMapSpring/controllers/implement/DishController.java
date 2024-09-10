package com.riwi.MealMapSpring.controllers.implement;

import com.riwi.MealMapSpring.controllers.InterfaceControllerEntity.IDishController;
import com.riwi.MealMapSpring.dtos.Request.DishRequest;
import com.riwi.MealMapSpring.dtos.Response.DishResponse;
import com.riwi.MealMapSpring.services.interfacesEntity.IDishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @Override
    @GetMapping("/available")
    @ResponseStatus(HttpStatus.OK)
    public List<DishResponse> getAvailableDish() {
        return this.dishService.getAvailableDish();
    }


}
