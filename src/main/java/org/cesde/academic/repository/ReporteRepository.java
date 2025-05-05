package org.cesde.academic.repository;

import org.cesde.academic.model.Reporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReporteRepository extends JpaRepository<Reporte, Integer> {
    List<Reporte> findByClase_Id(Integer claseId);
    List<Reporte> findByUsuario_Id(Integer usuarioId);
}
