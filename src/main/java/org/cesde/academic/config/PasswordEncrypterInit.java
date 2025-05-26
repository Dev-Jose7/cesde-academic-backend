package org.cesde.academic.config;

import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class PasswordEncrypterInit {

    @Bean
    public CommandLineRunner initEncryptPasswords(UsuarioRepository usuarioRepository) {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            List<Usuario> usuarios = usuarioRepository.findAll();

            for (Usuario usuario : usuarios) {
                String actual = usuario.getContrasena();

                // Solo encriptar si aún no está encriptada
                if (!actual.startsWith("$2a$")) {
                    usuario.setContrasena(encoder.encode(actual));
                    usuarioRepository.save(usuario);
                    System.out.println("Contraseña encriptada para usuario: " + usuario.getCorreo());
                }
            }

            System.out.println("Inicialización de contraseñas completada.");
        };
    }
}
