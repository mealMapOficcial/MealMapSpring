package com.riwi.MealMap.domain.ports.service;

import com.riwi.MealMap.application.dtos.request.Ingredient.TableWithoutId;
import com.riwi.MealMap.application.services.generic.Update;
import com.riwi.MealMap.application.services.generic.UpdateTable;
import com.riwi.MealMap.domain.entities.Table;

public interface ITableService extends
        UpdateTable<TableWithoutId, Integer> {
}
