package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.DesempenoRequestDTO;
import org.cesde.academic.dto.response.DesempenoResponseDTO;
import org.cesde.academic.enums.EstadoDesempeno;
import org.cesde.academic.enums.TipoModulo;
import org.cesde.academic.enums.TipoUsuario;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.exception.TipoIncorrectoException;
import org.cesde.academic.model.*;
import org.cesde.academic.repository.*;
import org.cesde.academic.service.IDesempenoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DesempenoServiceImpl implements IDesempenoService {

    @Autowired
    private DesempenoRepository desempenoRepository;
    
    @Autowired
    private UsuarioRepository usuarioRepository;
    
    @Autowired
    private ModuloRepository moduloRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private GrupoRepository grupoRepository;

    @Autowired
    private GrupoEstudianteRepository grupoEstudianteRepository;

    @Override
    public DesempenoResponseDTO createDesempeno(DesempenoRequestDTO request) {
        Desempeno desempeno = createEntity(request);
        return createResponse(desempeno);
    }

    @Override
    public List<DesempenoResponseDTO> getDesempenos() {
        return createResponseList(desempenoRepository.findAll());
    }

    @Override
    public DesempenoResponseDTO getDesempenoById(Integer id) {
        return createResponse(getDesempenoByIdOrException(id));
    }

    @Override
    public List<DesempenoResponseDTO> getDesempenoByEstudiante(Integer id) {
        return createResponseList(desempenoRepository.findAllByEstudianteId(id));
    }

    @Override
    public List<DesempenoResponseDTO> getDesempenoByModulo(Integer id) {
        return createResponseList(desempenoRepository.findAllByModuloId(id));
    }

    @Override
    public List<DesempenoResponseDTO> getDesempenoByEstado(EstadoDesempeno estado) {
        return createResponseList(desempenoRepository.findAllByEstado(estado));
    }

    @Override
    public DesempenoResponseDTO updateDesempeno(Integer id, DesempenoRequestDTO request) {
        Desempeno old = getDesempenoByIdOrException(id);

        Desempeno updated = createEntity(request);
        updated.setId(old.getId());
        updated.setCreado(old.getCreado());

        return createResponse(desempenoRepository.save(updated));
    }

    @Override
    public void deleteDesempeno(Integer id) {
        Desempeno desempeno = getDesempenoByIdOrException(id);
        desempenoRepository.delete(desempeno);
    }

    private Desempeno getDesempenoByIdOrException(Integer id) {
        return desempenoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Desempeño no encontrada"));
    }

    private Usuario getEstudianteOrException(Integer estudianteId) {
        Usuario usuario = usuarioRepository.findById(estudianteId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Estudiante no encontrado"));

        if (!usuario.getTipo().equals(TipoUsuario.ESTUDIANTE)) {
            throw new TipoIncorrectoException("El usuario debe ser un estudiante");
        }

        return usuario;
    }

    private Modulo getModuloByIdOrException(Integer id) {
        return moduloRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Modulo no encontrado"));
    }

    private List<Clase> getClaseByModuloOrException(Integer id) {
        List<Clase> clases = claseRepository.findAllByModuloId(id);

        if(clases.isEmpty()){
            throw new RecursoNoEncontradoException("Modulo no existente");
        }

        return clases;
    }

    private List<Grupo> getGrupoByCodigoOrException(String codigo){
        List<Grupo> grupos = grupoRepository.findAllByCodigoContainingIgnoreCase(codigo);

        if(grupos.isEmpty()){
            throw new RecursoNoEncontradoException("Grupo no existente");
        }

        return grupos;
    }

    // ID del modulo
    private void validateRelationEstudiante(Integer estudianteId, Integer moduloId){
        if(!getModuloByIdOrException(moduloId).getTipo().equals(TipoModulo.MATERIA)){
            return;
        }

        List<Clase> clase = getClaseByModuloOrException(moduloId);
        List<Grupo> grupo = getGrupoByCodigoOrException(clase.getFirst().getGrupo().getCodigo());
        List<GrupoEstudiante> relations = grupoEstudianteRepository.findByGrupo_Id(grupo.getFirst().getId());

        Optional<GrupoEstudiante> validation = relations.stream()
                .filter(relation -> relation.getEstudiante().getId().equals(estudianteId))
                .findFirst();

        validation.orElseThrow(() -> new RecursoNoEncontradoException("El estudiante no tiene relación con el modulo"));
    }

    private Desempeno createEntity(DesempenoRequestDTO request) {
        validateRelationEstudiante(request.getEstudianteId(), request.getModuloId());

        Desempeno Desempeno = new Desempeno();
        Desempeno.setEstudiante(getEstudianteOrException(request.getEstudianteId()));
        Desempeno.setModulo(getModuloByIdOrException(request.getModuloId()));
        Desempeno.setCalificacion(request.getCalificacion());
        Desempeno.setEstado(request.getEstado() == null ? EstadoDesempeno.CURSANDO : request.getEstado());

        return Desempeno;
    }

    private DesempenoResponseDTO createResponse(Desempeno Desempeno) {
        return new DesempenoResponseDTO(
                Desempeno.getId(),
                Desempeno.getEstudiante().getNombre(),
                Desempeno.getModulo().getNombre(),
                Desempeno.getCalificacion(),
                Desempeno.getEstado(),
                Desempeno.getCreado(),
                Desempeno.getActualizado()
        );
    }

    private List<DesempenoResponseDTO> createResponseList(List<Desempeno> desempenos) {
        List<DesempenoResponseDTO> list = new ArrayList<>();
        for (Desempeno desempeno : desempenos) {
            list.add(createResponse(desempeno));
        }
        return list;
    }
    
}
