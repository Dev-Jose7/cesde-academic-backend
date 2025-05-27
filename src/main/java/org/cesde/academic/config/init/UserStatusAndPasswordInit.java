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
                boolean modified = false;

                if (usuario.getAccountNoExpired() == null) {
                    usuario.setAccountNoExpired(true);
                    modified = true;
                }

                if (usuario.getAccountNoLocked() == null) {
                    usuario.setAccountNoLocked(true);
                    modified = true;
                }

                if (usuario.getCredentialNoExpired() == null) {
                    usuario.setCredentialNoExpired(true);
                    modified = true;
                }

                if (usuario.getIsEnabled() == null) {
                    usuario.setIsEnabled(true);
                    modified = true;
                }

                String actual = usuario.getContrasena();
                if (actual != null && !actual.startsWith("$2a$")) {
                    usuario.setContrasena(encoder.encode(actual));
                    System.out.println("Contraseña encriptada para usuario: " + usuario.getCorreo());
                    modified = true;
                }

                if (modified) {
                    usuarioRepository.save(usuario);
                }
            }

            System.out.println("Inicialización de estado y contraseñas completada.");
        };
    }
}
