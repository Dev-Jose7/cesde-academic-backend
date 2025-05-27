package org.cesde.academic.config;

import org.cesde.academic.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())  // Desactiva CSRF porque en REST no se usan formularios ni cookies
                .httpBasic(Customizer.withDefaults())  // Autenticación HTTP Basic
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))  // Sin sesión
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated())  // Se autoriza a que todas las peticiones http sean respondidas si el usuario está autenticado.
                .build();  // Construye la configuración
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { // Configuración del segundo componente de Spring Security Authentication Mnager
        // Es el núcleo del proceso de autenticación normalmente se delega a uno o más AuthenticationProvider
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){ // Configuración del tercer componente de Spring Security AuthenticationProvider usando provider DAO (Estándar)
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // El proveedor necesita otros dos componentes PasswordEncoder y UserDetailsService
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ // Configuración del quinto componente de Spring Security PasswordEncoder (Estándar)
        // Se usa para comparar contraseñas cifradas:
        return new BCryptPasswordEncoder();
    }
}

//    Componente            Rol Principal
//SecurityFilterChain:      Configura filtros de seguridad
//AuthenticationManager:    Gestiona el proceso de autenticación
//AuthenticationProvider:   Verifica credenciales
//UserDetailsService:       Carga los datos del usuario
//PasswordEncoder:          Codifica/verifica contraseñas
//SecurityContext:          Guarda info del usuario autenticado
//AccessDecisionManager:    Decide si un usuario tiene acceso
//HttpSecurity:             DSL para configurar seguridad web