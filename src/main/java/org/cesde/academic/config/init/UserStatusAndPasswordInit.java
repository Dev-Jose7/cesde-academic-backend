package org.cesde.academic.config.init;

import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class UserStatusAndPasswordInit {

    @Bean
    public CommandLineRunner initializeUserStatusAndPasswords(UsuarioRepository usuarioRepository) {
        return args -> {
            BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

            List<Usuario> usuarios = usuarioRepository.findAll();

            for (Usuario usuario : usuarios) {
                // Inicializar campos de estado si son null, si no, dejarlos tal cual.
                if (!usuario.getAccountNoExpired()) {
                    usuario.setAccountNoExpired(true);
                }
                if (!usuario.getAccountNoLocked()) {
                    usuario.setAccountNoLocked(true);
                }
                if (!usuario.getCredentialNoExpired()) {
                    usuario.setCredentialNoExpired(true);
                }
                if (!usuario.getIsEnabled()) {
                    usuario.setIsEnabled(true);
                }

                // Encriptar contraseña si no está encriptada (no empieza con $2a$)
                String actual = usuario.getContrasena();
                if (!actual.startsWith("$2a$")) {
                    usuario.setContrasena(encoder.encode(actual));
                    System.out.println("Contraseña encriptada para usuario: " + usuario.getCorreo());
                }

                usuarioRepository.save(usuario);
            }

            System.out.println("Inicialización de estado y contraseñas completada.");
        };
    }
}
