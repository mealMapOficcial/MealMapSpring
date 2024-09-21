package com.riwi.MealMap.Valirdation;

import java.util.Arrays;
import com.riwi.MealMap.domain.enums.TypeOfDishes;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class InvalidateEnumValidation implements ConstraintValidator<InvalidateEnum, TypeOfDishes>{

    @Override
    public boolean isValid(TypeOfDishes value, ConstraintValidatorContext context) {
       if(value == null){
        return false;

       }
       return Arrays.asList(TypeOfDishes.values()).contains(value);
    }

}
