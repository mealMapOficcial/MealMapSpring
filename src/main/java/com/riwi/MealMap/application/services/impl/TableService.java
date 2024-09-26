package com.riwi.MealMap.application.services.impl;

import com.riwi.MealMap.application.dtos.exception.EntityAlreadyExistsException;
import com.riwi.MealMap.application.dtos.exception.GenericExceptions;
import com.riwi.MealMap.application.dtos.request.TableDTO;
import com.riwi.MealMap.application.dtos.request.TableWithoutId;
import com.riwi.MealMap.domain.entities.Table;
import com.riwi.MealMap.domain.ports.service.ITableService;
import com.riwi.MealMap.infrastructure.persistence.TableRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TableService implements ITableService {

    @Autowired
    private TableRepository tableRepository;

    @Override
    public ResponseEntity<Table> create(Table table) {
        if (tableRepository.existsById(table.getIdTable())) {
            throw new GenericExceptions("A table with ID " + table.getIdTable() + " already exists.");
        }

        Integer newId = generateTableIdByFloor(table.getFloor());

        table.setIdTable(newId);

        Table savedTable = tableRepository.save(table);
        return ResponseEntity.ok(savedTable);
    }


    @Override
    public List<Table> readAll() {
        return tableRepository.findAll();
    }

    @Override
    public TableWithoutId update(Integer id) {
        Table optionaTables = this.tableRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Not Found"));

        optionaTables.isAvailable();
        Table saveTable = this.tableRepository.save(optionaTables);

        return TableWithoutId
                .builder()
                .numberOfChairs(saveTable.getNumberOfChairs())
                .disponibility(saveTable.getDisponibility())
                .isAvailable(saveTable.isAvailable())
                .build();
    }

    @Override
    public TableDTO getAvailableTable(Integer numberOfPeople) throws GenericExceptions {

        List<Table> availableTables = tableRepository.findByDisponibilityTrueAndNumberOfChairsIn(List.of(numberOfPeople, numberOfPeople + 2));

        if (availableTables.isEmpty()) {
            throw new GenericExceptions("There are no tables available for " + numberOfPeople + " people.");
        }

        Table availableTable = availableTables.get(0);

        availableTable.setDisponibility(false);
        tableRepository.save(availableTable);

        return TableDTO.builder()
                .idTable(availableTable.getIdTable())
                .floor(availableTable.getFloor())
                .build();
    }

    private ResponseEntity<Table> createTable(Table table) {
        if (table == null) {
            return ResponseEntity.badRequest().build();
        }
        Table savedTable = tableRepository.save(table);
        return ResponseEntity.ok(savedTable);
    }

    private Integer generateTableIdByFloor(Integer floor) {

        Integer existingCount = tableRepository.countByFloor(floor);

        return floor * 100 + existingCount + 1;
    }
}
