package org.cesde.academic.repository;

import org.cesde.academic.model.Grupo;
import org.cesde.academic.model.GrupoEstudiante;
import org.cesde.academic.model.GrupoEstudianteId;
import org.cesde.academic.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GrupoEstudianteRepository extends JpaRepository<GrupoEstudiante, GrupoEstudianteId> {
    List<GrupoEstudiante> findByGrupo_Id(Integer grupoId);
    List<GrupoEstudiante> findByEstudiante_Id(Integer estudianteId);
    boolean existsByGrupoIdAndEstudianteId(Integer grupoId, Integer estudianteiD);
}
