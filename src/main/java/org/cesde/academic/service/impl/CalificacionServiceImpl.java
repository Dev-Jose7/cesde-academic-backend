package org.cesde.academic.service.impl;

import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.exception.TipoIncorrectoException;
import org.cesde.academic.model.Actividad;
import org.cesde.academic.model.Calificacion;
import org.cesde.academic.model.Clase;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.ActividadRepository;
import org.cesde.academic.repository.CalificacionRepository;
import org.cesde.academic.repository.ClaseRepository;
import org.cesde.academic.repository.UsuarioRepository;
import org.cesde.academic.service.ICalificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public Calificacion createCalificacion(Calificacion calificacion) {
        Actividad actividad = actividadRepository.findById(calificacion.getActividad().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Actividad no existente"));

        Usuario usuario = usuarioRepository.findById(calificacion.getEstudiante().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no existente"));

        if(!calificacion.getEstudiante().getTipo().equals(Usuario.Tipo.ESTUDIANTE)){
            throw new TipoIncorrectoException("El usuario debe ser un estudiante");
        }

        calificacion.setActividad(actividad);
        calificacion.setEstudiante(usuario);
        return calificacionRepository.save(calificacion);
    }

    @Override
    public List<Calificacion> getCalificaciones() {
        return calificacionRepository.findAll();
    }

    @Override
    public Optional<Calificacion> getCalificacionById(Integer id) {
        return calificacionRepository.findById(id);
    }

    @Override
    public List<Calificacion> getCalificacionesByActividadId(Integer actividadId) {
        return calificacionRepository.findByActividad_Id(actividadId);
    }

    @Override
    public List<Calificacion> getCalificacionesByEstudianteId(Integer estudianteId) {
        return calificacionRepository.findByEstudiante_Id(estudianteId);
    }

    @Override
    public Calificacion updateCalificacion(Calificacion calificacion, Calificacion calificacionUpdated) {
        Actividad actividad = actividadRepository.findById(calificacionUpdated.getActividad().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Actividad no existente"));

        Usuario usuario = usuarioRepository.findById(calificacionUpdated.getEstudiante().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no existente"));

        if(!calificacion.getEstudiante().getTipo().equals(Usuario.Tipo.ESTUDIANTE)){
            throw new TipoIncorrectoException("El usuario debe ser un estudiante");
        }

        calificacionUpdated.setId(calificacion.getId()); // Aquí asignamos el id de la entidad existente.
        calificacionUpdated.setActividad(actividad);
        calificacionUpdated.setEstudiante(usuario);
        return calificacionRepository.save(calificacionUpdated); // Esto ahora realiza un UPDATE en lugar de un INSERT.
    }

    @Override
    public void deleteCalificacion(Calificacion calificacion) {
        calificacionRepository.delete(calificacion);
    }
}
