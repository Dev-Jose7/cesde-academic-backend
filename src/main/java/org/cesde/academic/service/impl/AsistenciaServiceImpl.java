package org.cesde.academic.service.impl;

import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.exception.TipoIncorrectoException;
import org.cesde.academic.model.Asistencia;
import org.cesde.academic.model.Clase;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.AsistenciaRepository;
import org.cesde.academic.repository.ClaseRepository;
import org.cesde.academic.repository.UsuarioRepository;
import org.cesde.academic.service.IAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaServiceImpl implements IAsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Asistencia createAsistencia(Asistencia asistencia) {
        Clase clase = claseRepository.findById(asistencia.getClase().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no existente"));

        Usuario usuario = usuarioRepository.findById(asistencia.getEstudiante().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no existente"));

        if(!asistencia.getEstudiante().getTipo().equals(Usuario.Tipo.ESTUDIANTE)){
            throw new TipoIncorrectoException("El usuario debe ser un estudiante");
        }

        asistencia.setClase(clase);
        asistencia.setEstudiante(usuario);
        return asistenciaRepository.save(asistencia);
    }

    @Override
    public List<Asistencia> getAsistencias() {
        return asistenciaRepository.findAll();
    }

    @Override
    public Optional<Asistencia> getAsistenciaById(Integer id) {
        return asistenciaRepository.findById(id);
    }

    @Override
    public List<Asistencia> getAsistenciasByClaseId(Integer claseId) {
        return asistenciaRepository.findByClase_Id(claseId);
    }

    @Override
    public List<Asistencia> getAsistenciasByEstudianteId(Integer estudianteId) {
        return asistenciaRepository.findByEstudiante_Id(estudianteId);
    }

    @Override
    public Asistencia updateAsistencia(Asistencia asistencia, Asistencia asistenciaUpdated) {
        Clase clase = claseRepository.findById(asistenciaUpdated.getClase().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no existente"));

        Usuario usuario = usuarioRepository.findById(asistenciaUpdated.getEstudiante().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no existente"));

        if(!asistenciaUpdated.getEstudiante().getTipo().equals(Usuario.Tipo.ESTUDIANTE)){
            throw new TipoIncorrectoException("El usuario debe ser un estudiante");
        }

        asistenciaUpdated.setId(asistencia.getId());
        asistenciaUpdated.setClase(clase);
        asistenciaUpdated.setEstudiante(usuario);
        return asistenciaRepository.save(asistenciaUpdated); // Esto hará un UPDATE y no un INSERT
    }

    @Override
    public void deleteAsistencia(Asistencia asistencia) {
        asistenciaRepository.delete(asistencia);
    }
}
