package com.riwi.MealMap.domain.ports.service;

import com.riwi.MealMap.application.dtos.request.TableDTO;
import com.riwi.MealMap.application.dtos.request.TableWithoutId;
import com.riwi.MealMap.application.services.generic.Create;
import com.riwi.MealMap.application.services.generic.ReadAll;
import com.riwi.MealMap.application.services.generic.UpdateTable;
import com.riwi.MealMap.domain.entities.Table;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface ITableService extends

    UpdateTable<TableWithoutId, Integer>{

    ResponseEntity<Table> create(Table table);
    List<Table> readAll();
    TableDTO getAvailableTable(Integer numberOfPeople);
}
