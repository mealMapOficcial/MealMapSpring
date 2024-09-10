package com.riwi.MealMap.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Meal Map",
        version = "1.0",
        description = "This is a API of Meal Map"
))
public class OpenApiConfiguration {
}
