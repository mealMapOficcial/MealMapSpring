package com.riwi.MealMap.application.services.impl;

import com.riwi.MealMap.application.dtos.request.TableWithoutId;
import com.riwi.MealMap.domain.entities.Table;
import com.riwi.MealMap.domain.ports.service.ITableService;
import com.riwi.MealMap.infrastructure.persistence.TableRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TableService implements ITableService {

    @Autowired
    private TableRepository tableRepository;

    @Override
    public TableWithoutId update(Integer id) {
        Table optionaTables = this.tableRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NO FOund"));

                optionaTables.isAvailable();
                //save update table
        Table saveTable = this.tableRepository.save(optionaTables);

                 return TableWithoutId
                           .builder()
                           .numberOfChairs(saveTable.getNumberOfChairs())
                           .disponibility(saveTable.getDisponibility())
                           .isAvailable(saveTable.isAvailable())
                           .build();
    }

}
