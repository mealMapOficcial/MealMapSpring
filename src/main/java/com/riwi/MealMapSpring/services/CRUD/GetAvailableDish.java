package com.riwi.MealMapSpring.services.CRUD;

import java.util.List;

public interface GetAvailableDish<EntityResponse> {
    public List<EntityResponse> getAvailableDish();
}
