package com.riwi.MealMap.controllers;

import com.riwi.MealMap.application.dtos.exception.ExceptionsResponse;
import com.riwi.MealMap.application.dtos.request.DrinkWithoutId;
import com.riwi.MealMap.application.dtos.request.TableWithoutId;
import com.riwi.MealMap.domain.entities.Drink;
import com.riwi.MealMap.domain.entities.Table;
import com.riwi.MealMap.domain.ports.service.ITableService;
import com.riwi.MealMap.infrastructure.config.annotations.FetchOrders;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {

    @Autowired
    private ITableService tableService;

    @PostMapping("/create")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Table created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input", content = @Content(schema = @Schema(implementation = ExceptionsResponse.class)))
    })
    public ResponseEntity<?> create(@RequestBody Table table) {

        ResponseEntity<Table> tableEntity = this.tableService.create(table);
        return ResponseEntity.status(HttpStatus.CREATED).body(tableEntity);
    }

    @GetMapping("/readAll")
    @FetchOrders
    public List<Table> readAll() {
        return tableService.readAll();
    }
}

