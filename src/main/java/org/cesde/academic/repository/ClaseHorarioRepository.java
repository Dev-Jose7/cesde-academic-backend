package org.cesde.academic.repository;

import org.cesde.academic.model.ClaseHorario;
import org.cesde.academic.model.ClaseHorarioId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClaseHorarioRepository extends JpaRepository<ClaseHorario, ClaseHorarioId> {
    Optional<ClaseHorario> findById(ClaseHorarioId claseHorarioId);
    List<ClaseHorario> findByClase_Id(Integer claseId);
    List<ClaseHorario> findByHorario_Id(Integer horarioId);
}
