package org.cesde.academic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")  // Aplica CORS a todas las rutas
                .allowedOrigins("*")  // Permite cualquier dominio (CORS público)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // Métodos permitidos
                .allowedHeaders("*")  // Permite cualquier header
                .allowCredentials(true)  // Si deseas permitir credenciales (cookies, headers de autorización)
                .maxAge(3600);  // Opcional: el tiempo en segundos que los navegadores pueden almacenar la configuración CORS
    }
}
