package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.UsuarioRequestDTO;
import org.cesde.academic.dto.response.UsuarioResponseDTO;
import org.cesde.academic.enums.EstadoUsuario;
import org.cesde.academic.enums.NombreRole;
import org.cesde.academic.enums.TipoUsuario;
import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Role;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.RoleRepository;
import org.cesde.academic.repository.UsuarioRepository;
import org.cesde.academic.service.IUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UsuarioResponseDTO createUsuario(UsuarioRequestDTO request) {
        Usuario usuario = createEntity(request, null);
        return createResponse(usuarioRepository.save(usuario));
    }

    @Override
    public List<UsuarioResponseDTO> getUsuarios() {
        return createResponseList(usuarioRepository.findAll());
    }

    @Override
    public UsuarioResponseDTO getUsuarioById(Integer id) {
        return createResponse(getUsuarioByIdOrException(id));
    }

    @Override
    public List<UsuarioResponseDTO> getUsuarioByNombre(String nombre) {
        return createResponseList(usuarioRepository.findAllByNombreContainingIgnoreCase(nombre));
    }

    @Override
    public List<UsuarioResponseDTO> getUsuarioByCedula(String cedula) {
        return createResponseList(usuarioRepository.findAllByCedulaContainingIgnoreCase(cedula));
    }

    @Override
    public List<UsuarioResponseDTO> getUsuarioByCorreo(String correo) {
        return createResponseList(usuarioRepository.findAllByCorreoContainingIgnoreCase(correo));
    }

    @Override
    public List<UsuarioResponseDTO> getUsuarioByTipo(TipoUsuario tipo) {
        return createResponseList(usuarioRepository.findAllByTipo(tipo));
    }

    @Override
    public UsuarioResponseDTO updateUsuario(Integer id, UsuarioRequestDTO request) {
        Usuario updatedUsuario = createEntity(request, id);
        Usuario oldUsuario = getUsuarioByIdOrException(id);

        updatedUsuario.setId(oldUsuario.getId());
        updatedUsuario.setCreado(oldUsuario.getCreado());

        return createResponse(usuarioRepository.save(updatedUsuario));
    }

    @Override
    public void deleteUsuario(Integer id) {
        Usuario usuario = getUsuarioByIdOrException(id);
        usuarioRepository.delete(usuario);
    }

    // Métodos auxiliares

    private Usuario getUsuarioByIdOrException(Integer id){
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));
    }

    private void validateUniqueCorreo(String correo, Integer id){
        boolean exists = id == null
                ? usuarioRepository.existsByCorreoIgnoreCase(correo)
                : usuarioRepository.existsByCorreoIgnoreCaseAndIdNot(correo, id);
        if (exists)
            throw new RecursoExistenteException("Correo ya registrado");
    }

    private void validateUniqueCedula(String cedula, Integer id){
        boolean exists = id == null
                ? usuarioRepository.existsByCedula(cedula)
                : usuarioRepository.existsByCedulaAndIdNot(cedula, id);
        if (exists)
            throw new RecursoExistenteException("Cédula ya registrada");
    }

    private Usuario createEntity(UsuarioRequestDTO request, Integer id){
        validateUniqueCorreo(request.getCorreo(), id);
        validateUniqueCedula(request.getCedula(), id);

        Usuario usuario = new Usuario();
        usuario.setNombre(request.getNombre());
        usuario.setCedula(request.getCedula());
        usuario.setCorreo(request.getCorreo());
        usuario.setContrasena(request.getContrasena());
        usuario.setTipo(request.getTipo());
        usuario.setEstado(request.getEstado() == null ? EstadoUsuario.ACTIVO : request.getEstado());
        usuario.setIsEnabled(true);
        usuario.setAccountNoExpired(true);
        usuario.setAccountNoLocked(true);
        usuario.setCredentialNoExpired(true);
        usuario.setRoles(createRole(usuario.getTipo()));
        return usuario;
    }

    private Set<Role> createRole(TipoUsuario tipo){
        Set<Role> roles = new HashSet<>();

        switch (tipo){
            case ESTUDIANTE -> {
                roles.add(roleRepository.findByNombre(NombreRole.ESTUDIANTE)
                        .orElseThrow(() -> new RecursoNoEncontradoException("Role " + NombreRole.ESTUDIANTE + "no encontrado")));
            }

            case DOCENTE -> {
                roles.add(roleRepository.findByNombre(NombreRole.DOCENTE)
                        .orElseThrow(() -> new RecursoNoEncontradoException("Role " + NombreRole.DOCENTE + "no encontrado")));
            }

            case ADMINISTRATIVO -> {
                roles.add(roleRepository.findByNombre(NombreRole.ADMINISTRATIVO)
                        .orElseThrow(() -> new RecursoNoEncontradoException("Role " + NombreRole.ADMINISTRATIVO + "no encontrado")));
            }

            case DIRECTIVO -> {
                roles.add(roleRepository.findByNombre(NombreRole.DIRECTIVO)
                        .orElseThrow(() -> new RecursoNoEncontradoException("Role " + NombreRole.DIRECTIVO + "no encontrado")));
            }
        }

        return roles;
    }

    private UsuarioResponseDTO createResponse(Usuario usuario){
        return new UsuarioResponseDTO(
                usuario.getId(),
                usuario.getCedula(),
                usuario.getNombre(),
                usuario.getTipo(),
                usuario.getEstado(),
                usuario.getCreado(),
                usuario.getActualizado()
        );
    }

    private List<UsuarioResponseDTO> createResponseList(List<Usuario> usuarios){
        List<UsuarioResponseDTO> responseList = new ArrayList<>();
        for (Usuario usuario : usuarios){
            responseList.add(createResponse(usuario));
        }
        return responseList;
    }
}
