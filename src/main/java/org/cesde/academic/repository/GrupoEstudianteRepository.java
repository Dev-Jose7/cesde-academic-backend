package org.cesde.academic.repository;

import org.cesde.academic.model.GrupoEstudiante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoEstudianteRepository extends JpaRepository<GrupoEstudiante, Integer> {
    List<GrupoEstudiante> findByGrupo_Id(Integer grupoId);
    List<GrupoEstudiante> findByEstudiante_Id(Integer estudianteId);
}

