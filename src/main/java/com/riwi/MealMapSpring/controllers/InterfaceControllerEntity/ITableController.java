package com.riwi.MealMapSpring.controllers.InterfaceControllerEntity;

import com.riwi.MealMapSpring.dtos.Response.TablesResponse;
import org.springframework.http.ResponseEntity;

public interface ITableController
        {
            ResponseEntity<?> udapteTable (Long id);



}
