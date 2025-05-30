package org.cesde.academic.controller; // Define el paquete donde está ubicada esta clase

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.Valid;
import org.cesde.academic.dto.request.AuthRequestDTO;
import org.cesde.academic.dto.request.RefreshTokenRequest;
import org.cesde.academic.dto.response.AuthResponseDTO;
import org.cesde.academic.dto.response.UsuarioResponseDTO;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.JwtBlacklist;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.UsuarioRepository;
import org.cesde.academic.service.IJwtBlacklistService;
import org.cesde.academic.service.IUsuarioService;
import org.cesde.academic.service.impl.UserDetailsServiceImpl;
import org.cesde.academic.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager; // Componente principal para manejar autenticaciones
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken; // Token de autenticación basado en nombre de usuario y contraseña
import org.springframework.security.core.Authentication; // Representa una autenticación exitosa
import org.springframework.security.core.AuthenticationException; // Excepción lanzada si la autenticación falla
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*; // Anotaciones para controladores REST

import java.time.LocalDateTime;
import java.util.Base64; // Para codificar las credenciales en Base64
import java.util.Date;
import java.util.Map; // Para devolver una respuesta en formato JSON sencillo

@RestController // Indica que esta clase es un controlador REST que devuelve datos (no vistas HTML)
@RequestMapping("/auth") // Prefijo común para todas las rutas de este controlador
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private IJwtBlacklistService jwtBlacklistService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO request){
        return new ResponseEntity<>(userDetailsService.loginUser(request), HttpStatus.OK);
    }

    @PostMapping("/auth/refresh")
    public ResponseEntity<?> refreshToken(@RequestBody RefreshTokenRequest request) {
        try {
            // Validar token con firma y expiración
            DecodedJWT decodedJWT = jwtUtil.validateToken(request.getRefreshToken());

            // Validación adicional: ¿el refresh token está en blacklist?
            if (jwtBlacklistService.isRefreshTokenBlacklisted(request.getRefreshToken())) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Refresh token revocado o previamente utilizado");
            }

            String cedula = decodedJWT.getSubject();

            // Validar que el usuario aún exista y obtener sus detalles
            Authentication authentication = userDetailsService.authenticateRefreshToken(cedula);

            // Generar nuevo access token
            String newAccessToken = jwtUtil.createToken(authentication);

            return ResponseEntity.ok(new AuthResponseDTO(cedula, "Token renovado", newAccessToken, request.getRefreshToken(), true));

        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token inválido o expirado");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String requestHeader,
            @RequestBody RefreshTokenRequest refreshRequest) {

        if (requestHeader != null && requestHeader.startsWith("Bearer ")) {
            String accessToken = requestHeader.substring(7);
            DecodedJWT decodedAccessToken = jwtUtil.validateToken(accessToken);
            String cedula = decodedAccessToken.getSubject();
            Date accessExp = decodedAccessToken.getExpiresAt();

            // Validar y decodificar refresh token recibido en el body
            String refreshToken = refreshRequest.getRefreshToken();
            DecodedJWT decodedRefreshToken = jwtUtil.validateToken(refreshToken);
            Date refreshExp = decodedRefreshToken.getExpiresAt();

            // Buscar usuario por cédula
            Usuario usuario = userDetailsService.loadUsuarioByCedula(cedula);

            // Guardar ambos tokens en la blacklist
            jwtBlacklistService.createBlacklistTokens(accessToken, accessExp, refreshToken, refreshExp, usuario);
        }

        return ResponseEntity.ok(Map.of("message", "Logout exitoso"));
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
