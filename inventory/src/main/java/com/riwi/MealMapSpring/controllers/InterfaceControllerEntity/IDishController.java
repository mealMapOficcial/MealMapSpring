package com.riwi.MealMapSpring.controllers.InterfaceControllerEntity;

import com.riwi.MealMapSpring.controllers.generic.CreateController;
import com.riwi.MealMapSpring.dtos.Request.DishRequest;
import com.riwi.MealMapSpring.dtos.Response.DishResponse;
import com.riwi.MealMapSpring.services.CRUD.GetAvailableDish;


public interface IDishController extends

        CreateController<DishRequest, DishResponse>,
        GetAvailableDish<DishResponse> {
}
