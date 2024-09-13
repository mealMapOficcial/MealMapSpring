package com.riwi.MealMapSpring.services.interfacesEntity;

import com.riwi.MealMapSpring.dtos.Request.DishRequest;
import com.riwi.MealMapSpring.dtos.Response.DishResponse;
import com.riwi.MealMapSpring.services.CRUD.Create;
import com.riwi.MealMapSpring.services.CRUD.GetAvailableDish;

public interface IDishService extends
        Create<DishRequest, DishResponse>,
        GetAvailableDish<DishResponse> {
}
