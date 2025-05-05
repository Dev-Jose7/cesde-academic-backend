package org.cesde.academic.repository;

import org.cesde.academic.model.GrupoEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GrupoEstudianteRepository extends JpaRepository<GrupoEstudiante, Integer> {
}

