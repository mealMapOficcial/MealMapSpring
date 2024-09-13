package com.riwi.MealMapSpring.controllers.generic;

import java.util.List;

public interface ReadAllController<Entity> {
    public List<Entity> readAll();
}
