package com.riwi.MealMap.Valirdation;

import com.riwi.MealMap.domain.enums.TypeOfDishes;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class InvalidateEnumValidation implements ConstraintValidator<InvalidateEnum, TypeOfDishes>{

   
    private Enum<?>[] enumConstants;

    @Override
    public void initialize(InvalidateEnum annotation) {
        this.enumConstants = annotation.enumClass().getEnumConstants();
    }

    @Override
    public boolean isValid(TypeOfDishes value, ConstraintValidatorContext context) {
       if(value == null){
        return false;

       }

       for (Enum<?> constant : enumConstants) {
        if (constant.name().equals(value.name())) {
            return true;
        }
    }
       return false;
    }

}
