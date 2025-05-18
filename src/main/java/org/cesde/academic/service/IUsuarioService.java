package org.cesde.academic.service;

import org.cesde.academic.dto.request.UsuarioRequestDTO;
import org.cesde.academic.dto.response.UsuarioResponseDTO;
import org.cesde.academic.enums.TipoUsuario;

import java.util.List;

public interface IUsuarioService {
    UsuarioResponseDTO createUsuario(UsuarioRequestDTO request);
    List<UsuarioResponseDTO> getUsuarios();
    UsuarioResponseDTO getUsuarioById(Integer id);
    List<UsuarioResponseDTO> getUsuarioByNombre(String nombre);
    List<UsuarioResponseDTO> getUsuarioByCedula(String cedula);
    List<UsuarioResponseDTO> getUsuarioByCorreo(String correo);
    List<UsuarioResponseDTO> getUsuarioByTipo(TipoUsuario tipo);
    UsuarioResponseDTO updateUsuario(Integer id, UsuarioRequestDTO request);
    void deleteUsuario(Integer id);
}
