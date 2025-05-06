package org.cesde.academic.repository;

import org.cesde.academic.model.GrupoEstudiante;
import org.cesde.academic.model.GrupoEstudianteId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GrupoEstudianteRepository extends JpaRepository<GrupoEstudiante, GrupoEstudianteId> {
    Optional<GrupoEstudiante> findById(GrupoEstudianteId grupoEstudianteId);
    List<GrupoEstudiante> findByGrupo_Id(Integer grupoId);
    List<GrupoEstudiante> findByEstudiante_Id(Integer estudianteId);
}

