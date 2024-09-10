package com.riwi.MealMapSpring.configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(
        title = "Meal Map",
        version = "1.0",
        description = "Esto es un API"
))
public class OpenApiConfiguration {
}
