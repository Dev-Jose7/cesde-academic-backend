package org.cesde.academic.repository;

import org.cesde.academic.model.Calificacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CalificacionRepository extends JpaRepository<Calificacion, Integer> {
    List<Calificacion> findAllByActividadId(Integer actividadId);
    List<Calificacion> findAllByEstudianteId(Integer estudianteId);
    boolean existsByActividadIdAndEstudianteId(Integer actividadId, Integer estudianteId);
    boolean existsByActividadIdAndEstudianteIdAndIdNot(Integer actividadId, Integer estudianteId, Integer id);
}
