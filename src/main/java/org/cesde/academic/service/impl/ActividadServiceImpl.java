package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.ActividadRequestDTO;
import org.cesde.academic.dto.response.ActividadResponseDTO;
import org.cesde.academic.dto.response.ClaseResponseInfoDTO;
import org.cesde.academic.enums.TipoActividad;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Actividad;
import org.cesde.academic.model.Clase;
import org.cesde.academic.repository.ActividadRepository;
import org.cesde.academic.repository.ClaseRepository;
import org.cesde.academic.service.IActividadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

@Service
public class ActividadServiceImpl implements IActividadService {

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Override
    public ActividadResponseDTO createActividad(ActividadRequestDTO request) {
        Actividad actividad = createEntity(request);
        return createResponse(actividadRepository.save(actividad));
    }

    @Override
    public List<ActividadResponseDTO> getActividades() {
        return createResponseList(actividadRepository.findAll());
    }

    @Override
    public ActividadResponseDTO getActividadById(Integer id) {
        return createResponse(getActividadByIdOrException(id));
    }

    @Override
    public List<ActividadResponseDTO> getActividadesByClase(Integer claseId) {
        return createResponseList(actividadRepository.findAllByClaseId(claseId));
    }

    @Override
    public List<ActividadResponseDTO> getActividadesByTitulo(String titulo) {
        return createResponseList(actividadRepository.findAllByTitulo(titulo));
    }

    @Override
    public List<ActividadResponseDTO> getActividadesByTipo(TipoActividad tipo) {
        return createResponseList(actividadRepository.findAllByTipo(tipo));
    }

    @Override
    public ActividadResponseDTO updateActividad(Integer id, ActividadRequestDTO request) {
        Actividad oldActividad = getActividadByIdOrException(id);

        Actividad updated = createEntity(request);
        updated.setId(oldActividad.getId());
        updated.setCreado(oldActividad.getCreado());

        return createResponse(actividadRepository.save(updated));
    }

    @Override
    public void deleteActividad(Integer id) {
        Actividad actividad = getActividadByIdOrException(id);
        actividadRepository.delete(actividad);
    }

    // ---------- Métodos Auxiliares ----------

    private Actividad getActividadByIdOrException(Integer id) {
        return actividadRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Actividad no encontrada"));
    }

    private Clase getClaseOrException(Integer claseId) {
        return claseRepository.findById(claseId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no encontrada"));
    }

    private Actividad createEntity(ActividadRequestDTO request) {
        Actividad actividad = new Actividad();
        actividad.setClase(getClaseOrException(request.getClaseId()));
        actividad.setTitulo(request.getTitulo());
        actividad.setDescripcion(request.getDescripcion());
        actividad.setTipo(request.getTipo());
        actividad.setFechaEntrega(request.getFechaEntrega());
        return actividad;
    }

    private ActividadResponseDTO createResponse(Actividad actividad) {
        return new ActividadResponseDTO(
                actividad.getId(),
                createClaseInfo(actividad),
                actividad.getTitulo(),
                actividad.getDescripcion(),
                actividad.getTipo(),
                actividad.getFechaEntrega(),
                actividad.getCreado(),
                actividad.getActualizado()
        );
    }

    private List<ActividadResponseDTO> createResponseList(List<Actividad> actividades) {
        List<ActividadResponseDTO> responseList = new ArrayList<>();
        for (Actividad actividad : actividades) {
            responseList.add(createResponse(actividad));
        }
        return responseList;
    }

    private ClaseResponseInfoDTO createClaseInfo(Actividad actividad){
        return new ClaseResponseInfoDTO(
                actividad.getClase().getGrupo().getCodigo(),
                actividad.getClase().getDocente().getNombre(),
                actividad.getClase().getModulo().getNombre()
        );
    }
}
