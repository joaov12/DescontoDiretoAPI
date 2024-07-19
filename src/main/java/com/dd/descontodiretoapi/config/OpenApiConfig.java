package com.dd.descontodiretoapi.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(info = @Info(title = "API Desconto Direto!", version = "1.0", description = "API destinada a promover ofertas de pequenos e grandes comércios "))
public class OpenApiConfig {
}