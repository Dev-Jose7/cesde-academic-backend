package org.cesde.academic.repository;

import org.cesde.academic.model.Clase;
import org.cesde.academic.model.Grupo;
import org.cesde.academic.model.Modulo;
import org.cesde.academic.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClaseRepository extends JpaRepository<Clase, Integer> {
    List<Clase> findAllByGrupoId(Integer grupoId);
    List<Clase> findAllByDocenteId(Integer docenteId);
    List<Clase> findAllByModuloId(Integer moduloId);
    boolean existsByGrupoIdAndDocenteIdAndModuloId(Integer grupoId, Integer docenteId, Integer moduloId);

}
