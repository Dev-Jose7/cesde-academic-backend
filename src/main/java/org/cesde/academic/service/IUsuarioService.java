package org.cesde.academic.service;

import org.cesde.academic.model.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioService {

    Usuario createUsuario(Usuario usuario);
    List<Usuario> getUsuarios();
    Optional<Usuario> getUsuarioById(Integer id);
    Optional<Usuario> getUsuarioByCorreo(String correo);
    Usuario updateUsuario(Usuario usuario, Usuario usuarioUpdated);
    void deleteUsuario(Usuario usuario);
}
