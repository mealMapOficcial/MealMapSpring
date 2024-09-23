package com.riwi.MealMap.domain.ports.service;

import com.riwi.MealMap.application.dtos.request.TableWithoutId;
import com.riwi.MealMap.application.services.generic.Create;
import com.riwi.MealMap.application.services.generic.ReadAll;
import com.riwi.MealMap.application.services.generic.UpdateTable;
import com.riwi.MealMap.domain.entities.Table;

public interface ITableService extends
        Create<Table>,
        ReadAll<Table> {
}
