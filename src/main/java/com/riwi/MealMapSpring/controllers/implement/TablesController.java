package com.riwi.MealMapSpring.controllers.implement;

import com.riwi.MealMapSpring.Repository.Interfaces.ITablesRepository;
import com.riwi.MealMapSpring.controllers.InterfaceControllerEntity.ITableController;
import com.riwi.MealMapSpring.dtos.Response.TablesResponse;
import com.riwi.MealMapSpring.dtos.exception.ErrorResponse;
import com.riwi.MealMapSpring.services.interfacesEntity.ITableService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequestMapping("/tables")
@RestController
public class TablesController implements ITableController {

    @Autowired
    private ITableService tableService;

    @PutMapping("/available/{id}")
    @Override
    public ResponseEntity<?> udapteTable(
            @PathVariable Long id) {

        try{
            TablesResponse updateTable = this.tableService.update(id);
            return ResponseEntity.ok(updateTable);

        }catch (EntityNotFoundException e){
            return ResponseEntity.notFound().build();
    }
  }
}
