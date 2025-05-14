package org.cesde.academic.repository;

import org.cesde.academic.enums.TipoActividad;
import org.cesde.academic.model.Actividad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActividadRepository extends JpaRepository<Actividad, Integer> {
    List<Actividad> findAllByClaseId(Integer claseId);
    List<Actividad> findAllByTitulo(String titulo);
    List<Actividad> findAllByTipo(TipoActividad tipo);
}
