package org.cesde.academic.repository;

import org.cesde.academic.model.Clase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Integer> {
    List<Clase> findByGrupo_Id(Integer grupoId);
    List<Clase> findByDocente_Id(Integer docenteId);
    List<Clase> findByModulo_Id(Integer moduloId);
}
