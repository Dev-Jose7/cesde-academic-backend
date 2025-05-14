package org.cesde.academic.repository;

import org.cesde.academic.model.Asistencia;
import org.cesde.academic.enums.EstadoAsistencia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface AsistenciaRepository extends JpaRepository<Asistencia, Integer> {
    List<Asistencia> findAllByClaseId(Integer claseId);
    List<Asistencia> findAllByEstudianteId(Integer estudianteId);
    List<Asistencia> findAllByFechaBetween(LocalDate desde, LocalDate hasta);
    List<Asistencia> findAllByEstado(EstadoAsistencia estado);
}
