package com.camilosoto.prueba_tecnica.web.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI fondosApiOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Fondos API")
                        .version("1.0.0")
                        .description("Documentación interactiva de la API para gestión de fondos"));
    }
}
