package org.cesde.academic.repository;

import org.cesde.academic.model.Asistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {
    List<Asistencia> findByClase_Id(Integer claseId);
    List<Asistencia> findByEstudiante_Id(Integer estudianteId);
}
