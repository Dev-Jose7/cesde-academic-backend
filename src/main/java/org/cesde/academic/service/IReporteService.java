package org.cesde.academic.service;

import org.cesde.academic.model.Reporte;

import java.util.List;
import java.util.Optional;

public interface IReporteService {

    Reporte createReporte(Reporte reporte);
    List<Reporte> getReportes();
    Optional<Reporte> getReporteById(Integer id);
    List<Reporte> getReportesByClaseId(Integer claseId);
    List<Reporte> getReportesByUsuarioId(Integer usuarioId);
    Reporte updateReporte(Reporte reporte, Reporte reporteUpdated);
    void deleteReporte(Reporte reporte);
}
