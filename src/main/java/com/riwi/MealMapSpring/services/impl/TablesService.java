package com.riwi.MealMapSpring.services.impl;

import com.riwi.MealMapSpring.Entities.Tables;
import com.riwi.MealMapSpring.Repository.Interfaces.ITablesRepository;
import com.riwi.MealMapSpring.dtos.Response.TablesResponse;
import com.riwi.MealMapSpring.services.interfacesEntity.ITableService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class TablesService implements ITableService {

    @Autowired
    private ITablesRepository tablesRepository;

    @Override
    public TablesResponse update(Long id) {
        Tables optionaTables = this.tablesRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("NO FOund"));

                optionaTables.toggleAvaiable();
                //save update table
        Tables saveTable = this.tablesRepository.save(optionaTables);

                 return TablesResponse
                           .builder()
                           .numberTable(saveTable.getNumberTable())
                           .capacity(saveTable.getCapacity())
                           .isAvaliable(saveTable.isAvailable())
                           .build();
                }

}
