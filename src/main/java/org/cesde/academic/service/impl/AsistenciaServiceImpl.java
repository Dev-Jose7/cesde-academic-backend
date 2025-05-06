package org.cesde.academic.service.impl;

import org.cesde.academic.model.Asistencia;
import org.cesde.academic.repository.AsistenciaRepository;
import org.cesde.academic.service.IAsistenciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AsistenciaServiceImpl implements IAsistenciaService {

    @Autowired
    private AsistenciaRepository asistenciaRepository;

    @Override
    public Asistencia createAsistencia(Asistencia asistencia) {
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
        asistenciaUpdated.setId(asistencia.getId());
        return asistenciaRepository.save(asistenciaUpdated); // Esto hará un UPDATE y no un INSERT
    }

    @Override
    public void deleteAsistencia(Asistencia asistencia) {
        asistenciaRepository.delete(asistencia);
    }
}
