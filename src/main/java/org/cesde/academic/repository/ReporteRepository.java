package org.cesde.academic.repository;

import org.cesde.academic.model.Reporte;
import org.cesde.academic.enums.EstadoReporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
    List<Reporte> findAllByClaseId(Integer claseId);
    List<Reporte> findAllByUsuarioId(Integer usuarioId);
    List<Reporte> findAllByTitulo(String titulo);
    List<Reporte> findAllByFecha(LocalDate fecha);
    List<Reporte> findAllByEstado(EstadoReporte estado);
}
