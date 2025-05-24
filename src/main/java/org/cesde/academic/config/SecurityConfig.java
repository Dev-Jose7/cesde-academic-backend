package org.cesde.academic.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {// Configuración del primer componente de Spring security Security Filter Chain
        // Intercepta la petición para validarla a través de múltiples filtros y aplicar condiciones
        // contiene múltiples filtros como:
        //	•	UsernamePasswordAuthenticationFilter
        //	•	BasicAuthenticationFilter
        //	•	BearerTokenAuthenticationFilter (para JWT)
        //	•	CsrfFilter, ExceptionTranslationFilter, etc.

        return httpSecurity
                .csrf(csrf -> csrf.disable()) // Desactiva CSRF porque en REST no se usan formularios ni cookies.
                .httpBasic(Customizer.withDefaults()) // Define el métodø de autenticación (HTTP Basic (para pruebas simples)).
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) // Indica que no se debe usar sesión: cada petición es independiente.
                .authorizeHttpRequests(http -> { // Establece las condiciones después de pasar por los filtros de Spring Security
                    http.requestMatchers(HttpMethod.GET, "/usuario/lista").permitAll(); // Permitida para todos sin necesidad de autenticación (Endpoints público)
                    http.requestMatchers(HttpMethod.GET, "/usuario/buscar/**").hasAuthority("READ"); // Permitido para todos los usuarios autenticados con autorización "READ" (Endpoint privado)

                    // http.anyRequest().denyAll(); ////  bloquea todas las peticiones que no han sido configuradas, incluso si el usuario está autenticado y autorizado.
                    // http.anyRequest().authenticated(); //// Permite el acceso a todos los endpoints que no han sido configurados siempre y cuando estén autenticados

                    http.anyRequest().denyAll(); // Se recomienda usar denyAll debido a que bloquea los endpoints no configurados brindando mayor protección
                })
                .build(); // Construye la configuración para las peticiones HTTP
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception { // Configuración del segundo componente de Spring Security Authentication Mnager
        // Es el núcleo del proceso de autenticación normalmente se delega a uno o más AuthenticationProvider
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){ // Configuración del tercer componente de Spring Security AuthenticationProvider usando provider DAO (Estándar)
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        // El proveedor necesita otros dos componentes PasswordEncoder y UserDetailsService
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Bean
    public UserDetailsService userDetailsService(){ // Configuración del quinto componente de Spring Security UserDetailsService
        // Aquí se define cómo cargar los datos del usuario, si a través de un repositorio provieniente de la aplicación, cargarlos en memoria, etc.
        List<UserDetails> userDetailsList = new ArrayList<>();

        userDetailsList.add(User
                .withUsername("jose")
                .password("1234")
                .roles("ADMIN")
                .authorities("READ", "CREATE")
                .build());

        userDetailsList.add(User
                .withUsername("fernando")
                .password("5678")
                .roles("USER")
                .authorities("READ")
                .build());

        return new InMemoryUserDetailsManager(userDetailsList);

        // UserDetails especifica cómo cargar el usuario desde una base de datos o fuente externa.
        // Se simula obtención de usuario en base de datos y se carga en memoria
    }

    @Bean
    public PasswordEncoder passwordEncoder(){ // Configuración del quinto componente de Spring Security PasswordEncoder (Estándar)
        // Se usa para comparar contraseñas cifradas:
        return NoOpPasswordEncoder.getInstance(); // Se usa solo para pruebas
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