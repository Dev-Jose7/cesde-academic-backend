package org.cesde.academic.service.impl;

import org.cesde.academic.dto.request.CalificacionRequestDTO;
import org.cesde.academic.dto.response.CalificacionResponseDTO;
import org.cesde.academic.dto.response.ClaseResponseInfoDTO;
import org.cesde.academic.enums.TipoUsuario;
import org.cesde.academic.exception.RecursoExistenteException;
import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.exception.TipoIncorrectoException;
import org.cesde.academic.model.Actividad;
import org.cesde.academic.model.Calificacion;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.ActividadRepository;
import org.cesde.academic.repository.CalificacionRepository;
import org.cesde.academic.repository.UsuarioRepository;
import org.cesde.academic.service.ICalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CalificacionServiceImpl implements ICalificacionService {

    @Autowired
    private CalificacionRepository calificacionRepository;

    @Autowired
    private ActividadRepository actividadRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public CalificacionResponseDTO createCalificacion(CalificacionRequestDTO request) {
        validateActividadEstudianteUnique(request, null);
        Calificacion calificacion = createEntity(request);
        return createResponse(calificacionRepository.save(calificacion));
    }

    @Override
    public List<CalificacionResponseDTO> getCalificaciones() {
        return createResponseList(calificacionRepository.findAll());
    }

    @Override
    public CalificacionResponseDTO getCalificacionById(Integer id) {
        return createResponse(getCalificacionByIdOrException(id));
    }

    @Override
    public List<CalificacionResponseDTO> getCalificacionesByActividad(Integer actividadId) {
        return createResponseList(calificacionRepository.findAllByActividadId(actividadId));
    }

    @Override
    public List<CalificacionResponseDTO> getCalificacionesByEstudiante(Integer estudianteId) {
        return createResponseList(calificacionRepository.findAllByEstudianteId(estudianteId));
    }

    @Override
    public CalificacionResponseDTO updateCalificacion(Integer id, CalificacionRequestDTO request) {
        validateActividadEstudianteUnique(request, id);
        Calificacion old = getCalificacionByIdOrException(id);

        Calificacion updated = createEntity(request);
        updated.setId(old.getId());
        updated.setCreado(old.getCreado());

        return createResponse(calificacionRepository.save(updated));
    }

    @Override
    public void deleteCalificacion(Integer id) {
        Calificacion calificacion = getCalificacionByIdOrException(id);
        calificacionRepository.delete(calificacion);
    }

    // ---------- Métodos Auxiliares ----------

    private Calificacion getCalificacionByIdOrException(Integer id) {
        return calificacionRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Calificación no encontrada"));
    }

    private Actividad getActividadOrException(Integer actividadId) {
        return actividadRepository.findById(actividadId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Actividad no encontrada"));
    }

    private Usuario getEstudianteOrException(Integer estudianteId) {
        Usuario usuario = usuarioRepository.findById(estudianteId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Estudiante no encontrado"));

        if (!usuario.getTipo().equals(TipoUsuario.ESTUDIANTE)) {
            throw new TipoIncorrectoException("El usuario debe ser un estudiante");
        }

        return usuario;
    }



//    private void validateActividadEstudianteUnique(CalificacionRequestDTO request, Integer id) {
//        Optional<Calificacion> calificacion = calificacionRepository.findByActividadIdAndEstudianteId(request.getActividadId(), request.getEstudianteId());
//
//        if(id == null){ // Para crear
//            if(calificacion.isPresent()){
//                throw new RecursoExistenteException("Ya existe una calificación para este estudiante en la actividad especificada");
//            }
//        }
//
//        if (calificacion.isPresent() && !calificacion.get().getId().equals(id)) {
//            throw new RecursoExistenteException("Ya existe una calificación para este estudiante en la actividad especificada");
//        }
//    }

    private void validateActividadEstudianteUnique(CalificacionRequestDTO request, Integer id) {
        boolean existe = (id == null)
                ? calificacionRepository.existsByActividadIdAndEstudianteId(request.getActividadId(), request.getEstudianteId())
                : calificacionRepository.existsByActividadIdAndEstudianteIdAndIdNot(request.getActividadId(), request.getEstudianteId(), id);

        if (existe) {
            throw new RecursoExistenteException("Ya existe una calificación para este estudiante en la actividad especificada");
        }
    }


    private Calificacion createEntity(CalificacionRequestDTO request) {
        Calificacion calificacion = new Calificacion();
        calificacion.setActividad(getActividadOrException(request.getActividadId()));
        calificacion.setEstudiante(getEstudianteOrException(request.getEstudianteId()));
        calificacion.setFecha(request.getFecha());
        calificacion.setNota(request.getNota());

        return calificacion;
    }

    private CalificacionResponseDTO createResponse(Calificacion calificacion) {
        return new CalificacionResponseDTO(
                calificacion.getId(),
                calificacion.getActividad().getTipo(),
                calificacion.getEstudiante().getNombre(),
                calificacion.getFecha(),
                calificacion.getNota(),
                calificacion.getCreado(),
                calificacion.getActualizado()
        );
    }

    private List<CalificacionResponseDTO> createResponseList(List<Calificacion> calificaciones) {
        List<CalificacionResponseDTO> list = new ArrayList<>();
        for (Calificacion c : calificaciones) {
            list.add(createResponse(c));
        }
        return list;
    }
}
