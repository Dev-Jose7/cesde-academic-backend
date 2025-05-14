package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.GrupoEstudianteRequestDTO;
import org.cesde.academic.dto.response.GrupoEstudianteResponseDTO;
import org.cesde.academic.enums.TipoUsuario;
import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.exception.TipoIncorrectoException;
import org.cesde.academic.model.GrupoEstudiante;
import org.cesde.academic.model.GrupoEstudianteId;
import org.cesde.academic.model.Grupo;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.GrupoEstudianteRepository;
import org.cesde.academic.repository.GrupoRepository;
import org.cesde.academic.repository.UsuarioRepository;
import org.cesde.academic.service.IGrupoEstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GrupoEstudianteServiceImpl implements IGrupoEstudianteService {

    @Autowired
    private GrupoEstudianteRepository grupoEstudianteRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public GrupoEstudianteResponseDTO createGrupoEstudiante(GrupoEstudianteRequestDTO request) {
        GrupoEstudiante grupoEstudiante = createEntity(request);
        return createResponse(grupoEstudianteRepository.save(grupoEstudiante));
    }

    @Override
    public List<GrupoEstudianteResponseDTO> getGrupoEstudiantes() {
        List<GrupoEstudiante> grupoEstudiantes = grupoEstudianteRepository.findAll();
        return createResponseList(grupoEstudiantes);
    }

    @Override
    public GrupoEstudianteResponseDTO getGrupoEstudianteById(GrupoEstudianteId id) {
        GrupoEstudiante grupoEstudiante = getGrupoEstudianteByIdOrException(id);
        return createResponse(grupoEstudiante);
    }

    @Override
    public List<GrupoEstudianteResponseDTO> getGrupoEstudiantesByGrupoId(Integer grupoId) {
        List<GrupoEstudiante> grupoEstudiantes = grupoEstudianteRepository.findByGrupo_Id(grupoId);
        return createResponseList(grupoEstudiantes);
    }

    @Override
    public List<GrupoEstudianteResponseDTO> getGrupoEstudiantesByEstudianteId(Integer estudianteId) {
        List<GrupoEstudiante> grupoEstudiantes = grupoEstudianteRepository.findByEstudiante_Id(estudianteId);
        return createResponseList(grupoEstudiantes);
    }

    @Override
    public GrupoEstudianteResponseDTO updateGrupoEstudiante(GrupoEstudianteId id, GrupoEstudianteRequestDTO request) {
        GrupoEstudiante updatedGrupoEstudiante = createEntity(request);
        // Verifica si existe el registro original
        GrupoEstudiante oldGrupoEstudiante = getGrupoEstudianteByIdOrException(id);

        // Borra el antiguo registro
        grupoEstudianteRepository.delete(oldGrupoEstudiante);

        // Crea, Guarda y devuelve el nuevo registro
        return createResponse(grupoEstudianteRepository.save(updatedGrupoEstudiante));
    }

    @Override
    public void deleteGrupoEstudiante(GrupoEstudianteId id) {
        GrupoEstudiante grupoEstudiante = getGrupoEstudianteByIdOrException(id);
        grupoEstudianteRepository.delete(grupoEstudiante);
    }

    // Métodos auxiliares
    private GrupoEstudiante getGrupoEstudianteByIdOrException(GrupoEstudianteId id) {
        return grupoEstudianteRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("GrupoEstudiante no encontrado"));
    }

    private Grupo getGrupoByIdOrException(Integer id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Grupo no encontrado"));
    }

    private Usuario getEstudianteByIdOrException(Integer id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no encontrado"));

        if (usuario.getTipo() != TipoUsuario.ESTUDIANTE) {
            throw new TipoIncorrectoException("Solo se pueden asignar usuarios de tipo ESTUDIANTE");
        }

        return usuario;
    }

    private void validateGrupoEstudianteUnique(Integer grupoId, Integer estudianteId) {
        if (grupoEstudianteRepository.existsByGrupoIdAndEstudianteId(grupoId, estudianteId)) {
            throw new RecursoExistenteException("Ya existe una relación entre el grupo y el estudiante.");
        }
    }

    private GrupoEstudiante createEntity(GrupoEstudianteRequestDTO request) {
        validateGrupoEstudianteUnique(request.getGrupoId(), request.getEstudianteId());

        GrupoEstudiante grupoEstudiante = new GrupoEstudiante();
        grupoEstudiante.setGrupo(getGrupoByIdOrException(request.getGrupoId()));
        grupoEstudiante.setEstudiante(getEstudianteByIdOrException(request.getEstudianteId()));
        return grupoEstudiante;
    }

    private GrupoEstudianteResponseDTO createResponse(GrupoEstudiante grupoEstudiante) {
        return new GrupoEstudianteResponseDTO(
                grupoEstudiante.getGrupo().getId(),
                grupoEstudiante.getEstudiante().getId(),
                grupoEstudiante.getCreado(),
                grupoEstudiante.getActualizado()
        );
    }

    private List<GrupoEstudianteResponseDTO> createResponseList(List<GrupoEstudiante> grupoEstudiantes) {
        List<GrupoEstudianteResponseDTO> lista = new ArrayList<>();
        for (GrupoEstudiante grupoEstudiante : grupoEstudiantes) {
            lista.add(createResponse(grupoEstudiante));
        }
        return lista;
    }
}
