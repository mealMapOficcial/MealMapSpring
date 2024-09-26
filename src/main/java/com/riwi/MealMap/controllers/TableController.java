package com.riwi.MealMap.controllers;

import com.riwi.MealMap.application.dtos.exception.GenericExceptions;
import com.riwi.MealMap.application.dtos.request.TableDTO;
import com.riwi.MealMap.application.dtos.request.TableWithoutId;
import com.riwi.MealMap.domain.entities.Table;
import com.riwi.MealMap.domain.ports.service.ITableService;
import com.riwi.MealMap.infrastructure.config.annotations.FetchOrders;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tables")
@CrossOrigin("*")
public class TableController {

    @Autowired
    ITableService tableService;

    @PostMapping("/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Table created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = GenericExceptions.class)))
    })
    public ResponseEntity<?> create(@RequestBody Table table) {
        try {
            ResponseEntity<Table> tableEntity = tableService.create(table);
            return ResponseEntity.status(HttpStatus.CREATED).body(tableEntity);
        } catch (GenericExceptions ex) {
            return ResponseEntity.badRequest().body(new GenericExceptions(ex.getMessage()));
        }
    }

    @GetMapping("/readAll")
    public List<Table> readAll() {
        return tableService.readAll();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<TableWithoutId> updateTable(@PathVariable Integer id) {
        try {
            TableWithoutId updatedTable = tableService.update(id);
            return ResponseEntity.ok(updatedTable);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/available/{numberOfPeople}")
    public ResponseEntity<?> getAvailableTable(@PathVariable Integer numberOfPeople) {
        TableDTO tableDTO = tableService.getAvailableTable(numberOfPeople);

        return ResponseEntity.ok(tableDTO);
    }
}
