package com.riwi.MealMap.domain.ports.service;

import com.riwi.MealMap.application.dtos.response.SalesReportDTO;

public interface SalesReport {

    SalesReportDTO getSalesReportsOfDay();
}
