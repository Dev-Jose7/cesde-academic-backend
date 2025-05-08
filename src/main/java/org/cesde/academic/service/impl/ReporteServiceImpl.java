package org.cesde.academic.service.impl;

import org.cesde.academic.exception.RecursoNoEncontradoException;
import org.cesde.academic.model.Clase;
import org.cesde.academic.model.Reporte;
import org.cesde.academic.model.Usuario;
import org.cesde.academic.repository.ClaseRepository;
import org.cesde.academic.repository.ReporteRepository;
import org.cesde.academic.repository.UsuarioRepository;
import org.cesde.academic.service.IReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteServiceImpl implements IReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Autowired
    private ClaseRepository claseRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    public Reporte createReporte(Reporte reporte) {
        Clase clase = claseRepository.findById(reporte.getClase().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no existente"));

        Usuario usuario = usuarioRepository.findById(reporte.getUsuario().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no existente"));

        reporte.setClase(clase);
        reporte.setUsuario(usuario);
        return reporteRepository.save(reporte);
    }

    @Override
    public List<Reporte> getReportes() {
        return reporteRepository.findAll();
    }

    @Override
    public Optional<Reporte> getReporteById(Integer id) {
        return reporteRepository.findById(id);
    }

    @Override
    public List<Reporte> getReportesByClaseId(Integer claseId) {
        return reporteRepository.findByClase_Id(claseId);
    }

    @Override
    public List<Reporte> getReportesByUsuarioId(Integer usuarioId) {
        return reporteRepository.findByUsuario_Id(usuarioId);
    }

    @Override
    public Reporte updateReporte(Reporte reporte, Reporte reporteUpdated) {
        Clase clase = claseRepository.findById(reporteUpdated.getClase().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Clase no existente"));

        Usuario usuario = usuarioRepository.findById(reporteUpdated.getUsuario().getId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Usuario no existente"));


        reporteUpdated.setId(reporte.getId());
        reporteUpdated.setClase(clase);
        reporteUpdated.setUsuario(usuario);
        return reporteRepository.save(reporteUpdated); // Esto hará un UPDATE
    }

    @Override
    public void deleteReporte(Reporte reporte) {
        reporteRepository.delete(reporte);
    }
}
