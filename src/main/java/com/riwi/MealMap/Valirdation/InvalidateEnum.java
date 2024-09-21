package com.riwi.MealMap.Valirdation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = InvalidateEnumValidation.class)
public @interface InvalidateEnum {

    String message() default "Invalid type dish";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

}
