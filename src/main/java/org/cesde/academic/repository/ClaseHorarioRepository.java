package org.cesde.academic.repository;

import org.cesde.academic.model.Clase;
import org.cesde.academic.model.ClaseHorario;
import org.cesde.academic.model.ClaseHorarioId;
import org.cesde.academic.model.Horario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClaseHorarioRepository extends JpaRepository<ClaseHorario, ClaseHorarioId> {
    List<ClaseHorario> findByClase_Id(Integer id);
    List<ClaseHorario> findByHorario_Id(Integer id);
    boolean existsByClaseIdAndHorarioId(Integer claseId, Integer horarioId);
}
