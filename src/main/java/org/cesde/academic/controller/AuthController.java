package org.cesde.academic.controller; // Define el paquete donde está ubicada esta clase

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import jakarta.validation.Valid;
import org.cesde.academic.dto.request.AuthRequestDTO;
import org.cesde.academic.dto.request.RefreshTokenRequest;
import org.cesde.academic.dto.response.AuthResponseDTO;
import org.cesde.academic.dto.response.UsuarioResponseDTO;
import org.cesde.academic.enums.TipoToken;
import org.cesde.academic.enums.TipoUsuario;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.exception.TipoIncorrectoException;
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
import java.util.*;

@RestController // Indica que esta clase es un controlador REST que devuelve datos (no vistas HTML)
@RequestMapping("/auth") // Prefijo común para todas las rutas de este controlador
public class AuthController {

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtils jwtUtil;

    @Autowired
    private IJwtBlacklistService jwtBlacklistService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@Valid @RequestBody AuthRequestDTO request){
        return new ResponseEntity<>(userDetailsService.loginUser(request), HttpStatus.OK);
    }

    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@Valid @RequestBody RefreshTokenRequest request) {
        try {
            // 1. Validar el token con firma y expiración
            DecodedJWT decodedJWT = jwtUtil.validateToken(request.getRefreshToken());

            // 2. Revisar si el token está en la blacklist
            if (jwtBlacklistService.isTokenBlacklisted(request.getRefreshToken(), TipoToken.REFRESH)) {
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body("Refresh token revocado o previamente utilizado");
            }

            // 3. Extraer datos del token
            String cedula = decodedJWT.getSubject();
            Date refreshExp = decodedJWT.getExpiresAt();

            // 4. Revocar el refresh token usado
            Usuario usuario = userDetailsService.loadUsuarioByCedula(cedula);
            jwtBlacklistService.blacklistToken(request.getRefreshToken(), TipoToken.REFRESH, refreshExp, usuario);

            // 5. Autenticar nuevamente al usuario para renovar sus datos (roles/permisos)
            Authentication authentication = userDetailsService.authenticateToRefreshToken(cedula);

            // 6. Emitir nuevos tokens
            String newAccessToken = jwtUtil.createAccessToken(authentication);
            String newRefreshToken = jwtUtil.createRefreshToken(cedula);

            // 7. Responder con los nuevos tokens
            return ResponseEntity.ok(new AuthResponseDTO(
                    userDetailsService.createUsuarioDTO(cedula),
                    "Token renovado correctamente",
                    newAccessToken,
                    newRefreshToken,
                    true
            ));

        } catch (JWTVerificationException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Refresh token inválido o expirado");
        }
    }

    @GetMapping("/validate/{tipo}")
    public ResponseEntity<?> validateToken(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String request,
            @PathVariable("tipo") String tipo) {

        System.out.println(request);

        if (!request.startsWith("Bearer ")) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        try {
            TipoUsuario.valueOf(tipo.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw  new TipoIncorrectoException("Tipo de usuario inválido");
        }

        String token = request.substring(7);
        DecodedJWT decodedJWT = jwtUtil.validateToken(token);

        List<String> authorities = jwtUtil.getSpecificClaim(decodedJWT, "authorities").asList(String.class);
        boolean estado = authorities.contains("ROLE_" + TipoUsuario.valueOf(tipo.toUpperCase()));

        return estado
                ? new ResponseEntity<>(HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.FORBIDDEN);
    }


    @PostMapping("/logout")
    public ResponseEntity<?> logout(
            @RequestHeader(HttpHeaders.AUTHORIZATION) String requestHeader,
            @Valid @RequestBody RefreshTokenRequest refreshRequest) {

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
            jwtBlacklistService.blacklistToken(accessToken, TipoToken.ACCESS, accessExp, usuario);
            jwtBlacklistService.blacklistToken(refreshToken, TipoToken.REFRESH, refreshExp, usuario);
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
