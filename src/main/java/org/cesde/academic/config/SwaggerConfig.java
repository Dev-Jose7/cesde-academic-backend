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

// Swagger (ahora parte del estándar OpenAPI) es un conjunto de herramientas para documentar y consumir
// APIs RESTful. El componente central es el esquema OpenAPI, que es un documento (JSON o YAML) que describe
// tu API: rutas, métodos HTTP, parámetros, respuestas, errores, seguridad, etc.

// OpenAPI es un estándar para describir APIs REST. No es un software, sino un formato de especificación,
// como una receta escrita en JSON o YAML que explica cómo funciona tu API.

// Cuando usas Swagger en un proyecto Java con Spring Boot (con springdoc-openapi), Spring analiza tu
// código para generar un documento OpenAPI automáticamente. Si no configuras nada se generará un documento
// OpenApi genérico

// Al configurarlo podemos generar un OpenApi personalizado el cuál nos permite adaptar dicho documento al
// contexto de la aplicación brindado una documentación y consumos más ordenadas y profesionales. Además con
// el objeto Server podemos indicarle a Swagger UI desde qué dominio se puede llamar la API, ya que por defecto
// esta configurada para ser leida en el localhost, en resumen: Tu API está hosteada en un dominio distinto
// al de la documentación es necesario añadirle un Server.