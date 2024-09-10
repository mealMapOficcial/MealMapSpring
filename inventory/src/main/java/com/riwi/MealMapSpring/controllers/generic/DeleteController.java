package com.riwi.MealMapSpring.controllers.generic;

public interface DeleteController<ID> {
    public void destroy(ID id);
}
