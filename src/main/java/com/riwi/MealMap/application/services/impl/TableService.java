package com.riwi.MealMap.application.services.impl;

import com.riwi.MealMap.application.dtos.request.TableWithoutId;
import com.riwi.MealMap.domain.entities.Table;
import com.riwi.MealMap.domain.ports.service.ITableService;
import com.riwi.MealMap.infrastructure.persistence.TableRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class TableService implements ITableService {

    @Autowired
    private TableRepository tableRepository;

    @Override
    public ResponseEntity<Table> create(Table table) {
        if (table == null) {
            return ResponseEntity.badRequest().build(); // Retorna un 400 Bad Request si la tabla es nula
        }
        Table savedTable = tableRepository.save(table);
        return ResponseEntity.ok(savedTable);
    }

    @Override
    public List<Table> readAll() {
        return tableRepository.findAll();
    }
}
