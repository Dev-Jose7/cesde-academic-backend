package org.cesde.academic.repository;

import org.cesde.academic.model.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Integer> {
    List<Grupo> findAllByProgramaIdAndSemestreId(Integer programaId, Integer semestreId);
    List<Grupo> findAllByProgramaId(Integer programaId);
    List<Grupo> findAllBySemestreId(Integer semestreId);
    List<Grupo> findByCodigoContainingIgnoreCase(String codigo);
    boolean existsByCodigoIgnoreCase(String codigo);
    boolean existsByCodigoIgnoreCaseAndIdNot(String codigo, Integer id);
    Grupo findByCodigo(String codigo);

}
