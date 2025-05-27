package org.cesde.academic.controller; // Define el paquete donde está ubicada esta clase

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager; // Componente principal para manejar autenticaciones
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Token de autenticación basado en nombre de usuario y contraseña
import org.springframework.security.core.Authentication; // Representa una autenticación exitosa
import org.springframework.security.core.AuthenticationException; // Excepción lanzada si la autenticación falla
import org.springframework.web.bind.annotation.*; // Anotaciones para controladores REST

import java.util.Base64; // Para codificar las credenciales en Base64
import java.util.Map; // Para devolver una respuesta en formato JSON sencillo

@RestController // Indica que esta clase es un controlador REST que devuelve datos (no vistas HTML)
@RequestMapping("/auth") // Prefijo común para todas las rutas de este controlador
public class AuthController {

    @Autowired // Inyección de dependencias: Spring inyectará una instancia del AuthenticationManager automáticamente
    private AuthenticationManager authenticationManager;

    // Define un endpoint POST en /dev/auth/basic-token
    @PostMapping("/login")
    public Map<String, String> getBasicAuthToken(@RequestBody Map<String, String> credentials) {
        // Extrae la cédula (nombre de usuario) del cuerpo del request
        String cedula = credentials.get("cedula");

        // Extrae la contraseña del cuerpo del request
        String contrasena = credentials.get("contrasena");

        try {
            // Usa el AuthenticationManager para autenticar las credenciales
            // Esto valida el usuario con UserDetailsService y compara contraseñas con PasswordEncoder
            Authentication authentication = authenticationManager.authenticate(
                    // Crea un token de autenticación con las credenciales del usuario
                    new UsernamePasswordAuthenticationToken(cedula, contrasena)
            );

            // Si la autenticación fue exitosa, genera un token Base64 con el formato "cedula:contrasena"
            String token = Base64.getEncoder().encodeToString((cedula + ":" + contrasena).getBytes());

            // Devuelve un JSON con el token en formato Authorization: Basic ...
            return Map.of("basicAuthToken", "Basic " + token);

        } catch (AuthenticationException ex) {
            // Si la autenticación falla (credenciales incorrectas), lanza una excepción con mensaje personalizado
            throw new RuntimeException("Credenciales inválidas", ex);
        }
    }
}
