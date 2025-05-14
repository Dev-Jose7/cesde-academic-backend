package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.ClaseRequestDTO;
import org.cesde.academic.dto.response.ClaseResponseDTO;
import org.cesde.academic.enums.TipoUsuario;
import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.exception.TipoIncorrectoException;
import org.cesde.academic.model.Clase;
import org.cesde.academic.model.Grupo;
import org.cesde.academic.model.Modulo;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.ClaseRepository;
import org.cesde.academic.repository.GrupoRepository;
import org.cesde.academic.repository.ModuloRepository;
import org.cesde.academic.repository.UsuarioRepository;
import org.cesde.academic.service.IClaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClaseServiceImpl implements IClaseService {

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ModuloRepository moduloRepository;

    @Override
    public ClaseResponseDTO createClase(ClaseRequestDTO request) {
        Clase clase = createEntity(request);
        return createResponse(claseRepository.save(clase));
    }

    @Override
    public List<ClaseResponseDTO> getClases() {
        return createResponseList(claseRepository.findAll());
    }

    @Override
    public ClaseResponseDTO getClaseById(Integer id) {
        return createResponse(getClaseByIdOrException(id));
    }

    @Override
    public List<ClaseResponseDTO> getClasesByGrupo(Integer grupoId) {
        return createResponseList(claseRepository.findAllByGrupoId(grupoId));
    }

    @Override
    public List<ClaseResponseDTO> getClasesByDocente(Integer docenteId) {
        return createResponseList(claseRepository.findAllByDocenteId(docenteId));
    }

    @Override
    public List<ClaseResponseDTO> getClasesByModulo(Integer moduloId) {
        return createResponseList(claseRepository.findAllByModuloId(moduloId));
    }

    @Override
    public ClaseResponseDTO updateClase(Integer id, ClaseRequestDTO request) {
        Clase oldClase = getClaseByIdOrException(id);

        Clase updated = createEntity(request);
        updated.setId(oldClase.getId());
        updated.setCreado(oldClase.getCreado());

        return createResponse(claseRepository.save(updated));
    }

    @Override
    public void deleteClase(Integer id) {
        Clase clase = getClaseByIdOrException(id);
        claseRepository.delete(clase);
    }

    // ---------- Métodos Auxiliares ----------

    private Clase getClaseByIdOrException(Integer id) {
        return claseRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no encontrada"));
    }

    private Grupo getGrupoOrException(Integer grupoId) {
        return grupoRepository.findById(grupoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Grupo no encontrado"));
    }

    private Usuario getDocenteOrException(Integer docenteId) {
        Usuario usuario = usuarioRepository.findById(docenteId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Docente no encontrado"));

        if(!usuario.getTipo().equals(TipoUsuario.DOCENTE)){
            throw new TipoIncorrectoException("El usuario debe ser un docente");
        }

        return usuario;
    }

    private Modulo getModuloOrException(Integer moduloId) {
        return moduloRepository.findById(moduloId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Módulo no encontrado"));
    }

    private void ValidateGrupoAndDocenteAndModulo(Integer grupoId, Integer docenteId, Integer moduloId){
        if(claseRepository.existsByGrupoIdAndDocenteIdAndModuloId(grupoId, docenteId, moduloId)){
            throw new RecursoExistenteException("Ya existe una clase registrada bajo los datos indicados");
        }
    }

    private Clase createEntity(ClaseRequestDTO request) {
        ValidateGrupoAndDocenteAndModulo(request.getGrupoId(), request.getDocenteId(), request.getModuloId());

        Clase clase = new Clase();
        clase.setGrupo(getGrupoOrException(request.getGrupoId()));
        clase.setDocente(getDocenteOrException(request.getDocenteId()));
        clase.setModulo(getModuloOrException(request.getModuloId()));
        return clase;
    }

    private ClaseResponseDTO createResponse(Clase clase) {
        return new ClaseResponseDTO(
                clase.getId(),
                clase.getGrupo().getCodigo(),
                clase.getDocente().getNombre(),
                clase.getModulo().getNombre(),
                clase.getCreado(),
                clase.getActualizado()
        );
    }

    private List<ClaseResponseDTO> createResponseList(List<Clase> clases) {
        List<ClaseResponseDTO> responseList = new ArrayList<>();
        for (Clase clase : clases) {
            responseList.add(createResponse(clase));
        }
        return responseList;
    }
}
