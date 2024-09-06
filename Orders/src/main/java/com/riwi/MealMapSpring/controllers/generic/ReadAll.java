package com.riwi.MealMapSpring.controllers.generic;

import java.util.List;

public interface ReadAll<Entity> {
    public List<Entity> readAll();
}
