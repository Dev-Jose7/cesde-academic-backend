package org.cesde.academic.controller; // Define el paquete donde está ubicada esta clase

import org.cesde.academic.dto.response.UsuarioResponseDTO;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @Autowired
    private UsuarioRepository usuarioRepository;

    // Define un endpoint POST en /auth/login
    @PostMapping("/login")
    public ResponseEntity<UsuarioResponseDTO> getBasicAuthToken(@RequestBody Map<String, String> credentials) {
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

            Usuario usuario = usuarioRepository.findByCedula(cedula)
                    .orElseThrow(() -> new RecursoNoEncontradoException("El usuario no existe"));

            UsuarioResponseDTO response = new UsuarioResponseDTO(
                    usuario.getId(),
                    usuario.getCedula(),
                    usuario.getNombre(),
                    usuario.getTipo(),
                    usuario.getEstado(),
                    usuario.getCreado(),
                    usuario.getActualizado()
            );

            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (AuthenticationException ex) {
            // Si la autenticación falla (credenciales incorrectas), lanza una excepción con mensaje personalizado
            throw new RuntimeException("Credenciales inválidas", ex);
        }
    }
}

// Esto es una especie de “híbrido”:
// Se usará un login tipo formulario en el frontend donde el usuario pone su cédula y contraseña.
// En vez de enviar esas credenciales en cada petición desde el frontend usando Base64 en JS, se genera un token en Base64 (tipo Basic <base64(cedula:contrasena)>) una sola vez y se reutiliza en las peticiones siguientes.

// Usar el endpoint /auth/login para:
// Validar credenciales.
// Generar un token Basic base64(cedula:contrasena).
// Enviarlo al frontend.
// Luego, el frontend lo guarda (en localStorage o sessionStorage) y lo usa en cada petición:
