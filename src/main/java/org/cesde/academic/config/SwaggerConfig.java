package org.cesde.academic.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        Server prodServer = new Server()
                .url("https://cesde-academic-app-production.up.railway.app")
                .description("Servidor en producción");

        return new OpenAPI()
                .info(new Info()
                        .title("API CESDE Academic")
                        .version("1.0")
                        .description("Documentación de la API para el sistema académico")
                        .contact(new Contact()
                                .name("Equipo CESDE")
                                .email("soporte@cesde.edu.co")
                                .url("https://cesde.edu.co")))
                .servers(List.of(prodServer));
    }
}
