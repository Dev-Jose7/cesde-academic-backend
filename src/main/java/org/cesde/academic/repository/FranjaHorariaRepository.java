package org.cesde.academic.repository;

import org.cesde.academic.model.FranjaHoraria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface FranjaHorariaRepository extends JpaRepository<FranjaHoraria, Integer> {
    List<FranjaHoraria> findAllByHoraInicio(LocalTime horaInicio);
    List<FranjaHoraria> findAllByHoraFin(LocalTime horaFin);
    boolean existsByHoraInicioAndHoraFin(LocalTime horaInicio, LocalTime horaFin);
    boolean existsByHoraInicioAndHoraFinAndIdNot(LocalTime horaInicio, LocalTime horaFin, Integer id);
}
