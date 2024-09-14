package com.riwi.MealMap.services.impl;

import com.riwi.MealMap.dtos.response.SalesReportDTO;
import com.riwi.MealMap.entities.*;
import com.riwi.MealMap.interfaces.DishesByOrderRepository;
import com.riwi.MealMap.interfaces.DrinksByOrderRepository;
import com.riwi.MealMap.services.interfaces.IOrderService;
import com.riwi.MealMap.services.interfaces.SalesReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SalesReportImpl implements SalesReport {

    @Autowired
    private IOrderService orderService;

    @Autowired
    private DrinksByOrderRepository drinksByOrderRepository;

    @Autowired
    private DishesByOrderRepository dishesByOrderRepository;

    @Override
    public SalesReportDTO getSalesReportsOfDay() {
        List<Order> orders = orderService.readOrdersOfTheDay();
        List<DrinksByOrder> drinksByOrders = drinksByOrderRepository.findByOrderIn(orders);
        List<DishesByOrder> dishesByOrders = dishesByOrderRepository.findByOrderIn(orders);

        List<Drink> drinkList = drinksByOrders.stream()
                .map(DrinksByOrder::getDrink)
                .toList();
        List<Dish> dishList = dishesByOrders.stream()
                .map(DishesByOrder::getDish)
                .toList();

        Integer dishesQuantity = dishList.size();
        Integer drinksQuantity = drinkList.size();

        Double totalDishesPrice = dishList.stream()
                .mapToDouble(Dish::getPriceOfDish)
                .sum();
        Double totalDrinksPrice = drinkList.stream()
                .mapToDouble(Drink::getPriceOfDrink)
                .sum();

        Double totalSales = totalDishesPrice + totalDrinksPrice;

        Integer totalOrdersQuantity = orders.size();

        Double costDishesPrice = dishList.stream()
                .mapToDouble(Dish::valueOfDish)
                .sum();
        Double costDrinksPrice = drinkList.stream()
                .mapToDouble(Drink::valueOfDrink)
                .sum();

        Double totalCostPrice = costDishesPrice + costDrinksPrice;

        Double profitPrice = totalSales - totalCostPrice;

        return SalesReportDTO.builder()
                .dishesQuantity(dishesQuantity)
                .drinksQuantity(drinksQuantity)
                .totalDishesPrice(totalDishesPrice)
                .totalDrinksPrice(totalDrinksPrice)
                .totalSales(totalSales)
                .totalOrdersQuantity(totalOrdersQuantity)
                .costDishesPrice(costDishesPrice)
                .costDrinksPrice(costDrinksPrice)
                .totalCostPrice(totalCostPrice)
                .profitPrice(profitPrice)
                .build();
    }
}
