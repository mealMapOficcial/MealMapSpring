package com.riwi.MealMap.domain.ports.service;

import com.riwi.MealMap.application.dtos.request.TableWithoutId;
import com.riwi.MealMap.application.services.generic.UpdateTable;

public interface ITableService extends
        UpdateTable<TableWithoutId, Integer> {
}
