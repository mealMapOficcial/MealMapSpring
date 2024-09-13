package com.riwi.MealMapSpring.controllers.generic;

import java.util.List;

public interface GetAvailableController<EntityResponse>{
    public List<EntityResponse> getAvailableDish();
}
