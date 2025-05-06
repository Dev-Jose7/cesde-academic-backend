package org.cesde.academic.service.impl;

import org.cesde.academic.model.Reporte;
import org.cesde.academic.repository.ReporteRepository;
import org.cesde.academic.service.IReporteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReporteServiceImpl implements IReporteService {

    @Autowired
    private ReporteRepository reporteRepository;

    @Override
    public Reporte createReporte(Reporte reporte) {
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
        reporteUpdated.setId(reporte.getId());
        return reporteRepository.save(reporteUpdated); // Esto hará un UPDATE
    }

    @Override
    public void deleteReporte(Reporte reporte) {
        reporteRepository.delete(reporte);
    }
}
