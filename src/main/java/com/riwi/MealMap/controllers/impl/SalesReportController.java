package com.riwi.MealMap.controllers.impl;

import com.riwi.MealMap.dtos.response.SalesReportDTO;
import com.riwi.MealMap.services.interfaces.SalesReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sales-report")
public class SalesReportController {

    @Autowired
    private SalesReport salesReportService;

    @GetMapping("/today")
    public ResponseEntity<SalesReportDTO> getSalesReportToday() {
        return ResponseEntity.ok(salesReportService.getSalesReportsOfDay());
    }
}
