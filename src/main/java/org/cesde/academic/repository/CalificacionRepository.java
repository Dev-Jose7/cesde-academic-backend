package org.cesde.academic.repository;

import org.cesde.academic.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
    List<Calificacion> findByActividad_Id(Integer actividadId);
    List<Calificacion> findByEstudiante_Id(Integer estudianteId);
}
